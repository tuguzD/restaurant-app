package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import android.content.Context
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
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
            else -> return@LaunchedEffect
        }
        mainViewModel.updateCurrentDestination(currentDestination)
        mainViewModel.updateOnNavigateUpAction(navController::navigateUp)
    }

    val appName = stringResource(R.string.app_name)

    NavHost(navController = navController, startDestination = OrderList.route) {
        composable(OrderList.route) {
            OrderListScreen(
                mainViewModel = mainViewModel,
                orderViewModel = orderViewModel,
                snackbarHostState = snackbarHostState,
                navController = navController,
            )
        }
        composable(
            route = "${OrderDetail.route}/{orderId}",
            arguments = listOf(
                navArgument(name = "orderId") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("orderId") ?: return@composable
            val nanoId = NanoId(id)
            val order = remember(nanoId) {
                orderViewModel.state.orders.first { it.id == nanoId }
            }

            val title = stringResource(R.string.order_detail)
            SideEffect {
                mainViewModel.updateTitle(title)
                mainViewModel.updateFilled(isFilled = false)
            }
            OrderDetailScreen(order)
        }
    }

//    NavHost(navController = navController, startDestination = OrderList.route) {
//        composable(OrderList.route) {
//            SideEffect { onTitleChanged(appName) }
//
//            Scaffold(
//                floatingActionButton = {
//                    ExtendedFloatingActionButton(
//                        text = { Text(stringResource(R.string.add_order)) },
//                        icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
//                        onClick = { navController.navigate(OrderCreate.route) },
//                    )
//                }
//            ) { padding ->
//                OrderList(
//                    modifier = Modifier.padding(padding),
//                    orders = orders,
//                    onOrderClick = {
//                        navController.navigate("${OrderDetail.route}/${it.id}")
//                    },
//                )
//            }
//        }
//        dialog(
//            route = OrderCreate.route,
//            dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
//        ) {
//            val onOrderCreateMessage = stringResource(R.string.order_created)
//            val dismissText = stringResource(R.string.dismiss)
//
//            OrderCreateDialog(
//                modifier = Modifier.fillMaxSize(),
//                onOrderCreate = { order ->
//                    scope.launch {
//                        orderViewModel.createOrder(order)
//                        withContext(Dispatchers.Main) {
//                            navController.popBackStack()
//                        }
//                        snackbarHostState.showSnackbar(
//                            message = onOrderCreateMessage,
//                            actionLabel = dismissText,
//                        )
//                    }
//                },
//                onNavigateUp = { navController.navigateUp() },
//            )
//        }
}

fun OrderMessageKind.message(context: Context): String = when (this) {
    OrderMessageKind.OrderAdded -> context.getString(R.string.order_created)
    OrderMessageKind.OrderDeleted -> context.getString(R.string.order_deleted)
    else -> context.getString(R.string.unknown_error)
}
