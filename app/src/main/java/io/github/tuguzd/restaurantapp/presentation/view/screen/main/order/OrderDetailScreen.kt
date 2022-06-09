package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.order.OrderScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.util.OrderWaiterOrderItemCard
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.EntityProperty
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.roleMatches
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Application screen with information about provided [order].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(
    order: Order,
    orderItems: List<OrderItemData>,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    val title = stringResource(R.string.order_detail)
    SideEffect {
        mainViewModel.updateTitle(title)
        mainViewModel.updateFilled(isFilled = false)
    }

    var expanded by remember { mutableStateOf(false) }
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { onRefresh() }
    ) {
        Scaffold(
            floatingActionButton = {
                if (accountViewModel.state.roleMatches(UserType.Waiter)) {
                    ExtendedFloatingActionButton(
                        text = {
                            Text(stringResource(R.string.add_order_item))
                        },
                        icon = {
                            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                        },
                        onClick = { /*navController.navigate(OrderCreate.route)*/ },
                        expanded = expanded,
                    )
                }
            }
        ) { padding ->
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item { OrderDetail(order = order) }
                if (accountViewModel.state.roleMatches(UserType.Waiter) &&
                    orderItems.none { !it.ready }
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Button(
                                onClick = { /* toska */ }
                            ) { Text("Mark as purchased") }
                        }
                    }
                }
                items(orderItems, key = { it.id.toString() }) { orderItem ->
                    OrderWaiterOrderItemCard(
                        isWaiter = accountViewModel.state.roleMatches(UserType.Waiter),
                        orderItem = orderItem,
                        onClick = {
                            navController.navigate(
                                "${OrderItemDetail.route}/${orderItem.id}"
                            )
                        },
                    )
                }
            }
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset }
            .distinctUntilChanged().collect {
                expanded = it == 0
                mainViewModel.updateFilled(isFilled = it > 0)
            }
    }
}

@Composable
fun OrderDetail(order: Order) {
    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize(),
    ) {
        EntityProperty(
            name = stringResource(R.string.serviceItemPoint),
            value = order.serviceItemPoint?.name.toString(),
        )
        Divider()

        EntityProperty(
            name = stringResource(R.string.client_count),
            value = order.clientCount.toString(),
        )
        Divider()

        order.description?.let {
            EntityProperty(
                name = stringResource(R.string.description),
                value = it,
            )
            Divider()
        }

        EntityProperty(
            name = stringResource(R.string.order_time),
            value = order.datetimeCreate,
        )
        Divider()

        EntityProperty(
            name = stringResource(R.string.purchased),
            value = order.purchased.toString(),
        )
        Divider()
    }
}
