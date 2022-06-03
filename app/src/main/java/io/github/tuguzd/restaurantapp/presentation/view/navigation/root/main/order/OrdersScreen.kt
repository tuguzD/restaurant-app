package io.github.tuguzd.restaurantapp.presentation.view.navigation.root.main.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.main.order.OrderScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.util.collectAsStateLifecycleAware
import io.github.tuguzd.restaurantapp.presentation.viewmodel.OrderViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Application screen which represents *Orders* main application destination.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun OrdersScreen(
    onTitleChanged: (String) -> Unit,
    orderViewModel: OrderViewModel = hiltViewModel(),
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
) {
    val appName = stringResource(R.string.app_name)

    val orders by orderViewModel.orders
        .collectAsStateLifecycleAware(listOf(), scope.coroutineContext)
    val snackbarHostState = remember { SnackbarHostState() }

    NavHost(navController = navController, startDestination = OrderList.route) {
        composable(OrderList.route) {
            SideEffect { onTitleChanged(appName) }

            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text(stringResource(R.string.add_order)) },
                        icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
                        onClick = { navController.navigate(OrderCreate.route) },
                    )
                }
            ) { padding ->
                OrderList(
                    modifier = Modifier.padding(padding),
                    orders = orders,
                    onOrderClick = {
                        navController.navigate("${OrderDetail.route}/${it.id}")
                    },
                )
            }
        }
        dialog(
            route = OrderCreate.route,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            val onOrderCreateMessage = stringResource(R.string.order_created)
            val dismissText = stringResource(R.string.dismiss)

            OrderCreateDialog(
                modifier = Modifier.fillMaxSize(),
                onOrderCreate = { order ->
                    scope.launch {
                        orderViewModel.createOrder(order)
                        withContext(Dispatchers.Main) {
                            navController.popBackStack()
                        }
                        snackbarHostState.showSnackbar(
                            message = onOrderCreateMessage,
                            actionLabel = dismissText,
                        )
                    }
                },
                onNavigateUp = { navController.navigateUp() },
            )
        }
        composable(
            route = "${OrderDetail.route}/{orderId}",
            arguments = listOf(
                navArgument("orderId") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("orderId") ?: return@composable
            val order by remember(id) { orderViewModel.readById(id) }
                .collectAsStateLifecycleAware(initial = null)

            SideEffect {
                order?.serviceItemPoint?.name?.let { onTitleChanged(it) }
            }
            order?.let { OrderDetailScreen(it) }
        }
    }
}
