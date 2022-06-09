package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.order.OrderScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.util.OrderList
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.roleMatches
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListScreen(
    mainViewModel: MainViewModel,
    orderViewModel: OrderViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    val appName = stringResource(R.string.app_name)
    SideEffect { mainViewModel.updateTitle(appName) }

    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            if (accountViewModel.state.roleMatches(UserType.Waiter)) {
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.add_order)) },
                    icon = { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) },
                    onClick = { navController.navigate(OrderCreate.route) },
                    expanded = expanded,
                )
            }
        }
    ) { padding ->
        OrderList(
            modifier = Modifier.padding(padding),
            orders = orderViewModel.state.orders,
            onOrderClick = {
                navController.navigate("${OrderDetail.route}/${it.id}")
            },
            isRefreshing = orderViewModel.state.isUpdating,
            onRefresh = { orderViewModel.updateOrders() },
            lazyListState = listState,
            orderViewModel = orderViewModel,
        )
    }

    orderViewModel.state.userMessages.firstOrNull()?.let { message ->
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(
                message = message.kind.message(context),
                actionLabel = context.getString(R.string.dismiss),
            )
            orderViewModel.userMessageShown(message.id)
        }
    }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .distinctUntilChanged().collect {
                expanded = it == 0
                mainViewModel.updateFilled(isFilled = it > 0)
            }
    }
}
