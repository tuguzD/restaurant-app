package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import android.content.Context
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.MainScreenDestinations
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.order.OrderScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderMessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderViewModel

/**
 * Application screen which represents *Orders* main application destination.
 */
@Composable
fun OrderScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    orderViewModel: OrderViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        currentRoute ?: return@LaunchedEffect
        val currentDestination = when {
            currentRoute == OrderList.route -> MainScreenDestinations.Orders
            currentRoute == OrderCreate.route -> OrderCreate
            OrderDetail.route in currentRoute -> OrderDetail
            OrderItemDetail.route in currentRoute -> OrderItemDetail
            else -> return@LaunchedEffect
        }
        mainViewModel.updateCurrentDestination(currentDestination)
        mainViewModel.updateOnNavigateUpAction(navController::navigateUp)
    }

    NavHost(navController = navController, startDestination = OrderList.route) {
        composable(OrderList.route) {
            OrderListScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                orderViewModel = orderViewModel,
                snackbarHostState = snackbarHostState,
            )
        }
        composable(OrderCreate.route) {
            OrderCreateScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                orderViewModel = orderViewModel,
            )
        }
        composable(
            route = "${OrderDetail.route}/{id}",
            arguments = listOf(
                navArgument(name = "id") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val nanoId = NanoId(id)
            val order = remember(nanoId) {
                orderViewModel.state.orders.first { it.id == nanoId }
            }
            OrderDetailScreen(
                order = order,
                onRefresh = { orderViewModel.updateOrderItems(order) },
                orderItems = orderViewModel.state.orderItems,
                isRefreshing = orderViewModel.state.isUpdating,
                navController = navController,
                mainViewModel = mainViewModel,
            )
        }
        composable(
            route = "${OrderItemDetail.route}/{id}",
            arguments = listOf(
                navArgument(name = "id") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val nanoId = NanoId(id)
            val orderItem = remember(nanoId) {
                orderViewModel.state.orderItems.first { it.id == nanoId }
            }
            OrderItemDetailScreen(
                orderItem = orderItem,
                mainViewModel = mainViewModel,
            )
        }
    }
}

fun OrderMessageKind.message(context: Context): String = when (this) {
    OrderMessageKind.OrderAdded -> context.getString(R.string.order_created)
    OrderMessageKind.OrderDeleted -> context.getString(R.string.order_deleted)
    OrderMessageKind.OrderPurchased -> context.getString(R.string.order_purchased)
    else -> context.getString(R.string.unknown_error)
}
