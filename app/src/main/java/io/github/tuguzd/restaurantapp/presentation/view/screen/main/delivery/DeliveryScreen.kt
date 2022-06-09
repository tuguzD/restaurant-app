package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.MainScreenDestinations
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.delivery.DeliveryScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.OrderItemDetailScreen
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.delivery.DeliveryMessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.delivery.DeliveryViewModel

/**
 * Application screen which represents *Delivery* main application destination.
 */
@Composable
fun DeliveryScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    deliveryViewModel: DeliveryViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        currentRoute ?: return@LaunchedEffect
        val currentDestination = when {
            currentRoute == DeliveryList.route -> MainScreenDestinations.Delivery
            DeliveryDetail.route in currentRoute -> DeliveryDetail
            else -> return@LaunchedEffect
        }
        mainViewModel.updateCurrentDestination(currentDestination)
        mainViewModel.updateOnNavigateUpAction(navController::navigateUp)
    }

    NavHost(navController = navController, startDestination = DeliveryList.route) {
        composable(DeliveryList.route) {
            DeliveryListScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                deliveryViewModel = deliveryViewModel,
                snackbarHostState = snackbarHostState,
            )
        }
        composable(
            route = "${DeliveryDetail.route}/{id}",
            arguments = listOf(
                navArgument(name = "id") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: return@composable
            val nanoId = NanoId(id)
            val orderItem = remember(nanoId) {
                deliveryViewModel.state.orderItems.first { it.id == nanoId }
            }
            OrderItemDetailScreen(
                orderItem = orderItem,
                mainViewModel = mainViewModel,
            )
        }
    }
}

fun DeliveryMessageKind.message(context: Context): String = when (this) {
    DeliveryMessageKind.OrderItemReady -> context.getString(R.string.order_deleted)
    DeliveryMessageKind.OrderItemDelivered -> context.getString(R.string.order_created)
    else -> context.getString(R.string.unknown_error)
}
