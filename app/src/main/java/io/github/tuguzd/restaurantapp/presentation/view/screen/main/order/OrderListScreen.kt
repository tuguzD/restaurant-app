package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.order.OrderScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun OrderListScreen(
    mainViewModel: MainViewModel,
    orderViewModel: OrderViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    val appName = stringResource(R.string.app_name)
    SideEffect { mainViewModel.updateTitle(appName) }

    OrderList(
        modifier = Modifier.fillMaxSize(),
        orders = orderViewModel.state.orders,
        onOrderClick = {
            navController.navigate("${OrderDetail.route}/${it.id}")
        },
        isRefreshing = orderViewModel.state.isUpdating,
        onRefresh = { orderViewModel.updateOrders() },
        lazyListState = listState,
    )

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
                mainViewModel.updateFilled(isFilled = it > 0)
            }
    }
}
