package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.delivery.DeliveryScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util.OrderItemList
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.delivery.DeliveryViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun DeliveryListScreen(
    mainViewModel: MainViewModel,
    deliveryViewModel: DeliveryViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController,
) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    val appName = stringResource(R.string.app_name)
    SideEffect { mainViewModel.updateTitle(appName) }

    OrderItemList(
        orderItems = deliveryViewModel.state.orderItems,
        onOrderItemClick = {
            navController.navigate("${DeliveryDetail.route}/${it.id}")
        },
        onRefresh = { deliveryViewModel.updateOrderItems() },
    )

    deliveryViewModel.state.userMessages.firstOrNull()?.let { message ->
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(
                message = message.kind.message(context),
                actionLabel = context.getString(R.string.dismiss),
            )
            deliveryViewModel.userMessageShown(message.id)
        }
    }
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .distinctUntilChanged().collect {
                mainViewModel.updateFilled(isFilled = it > 0)
            }
    }
}
