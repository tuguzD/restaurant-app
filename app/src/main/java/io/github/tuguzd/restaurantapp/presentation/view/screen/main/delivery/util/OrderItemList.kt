package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItem
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.roleMatches

@Composable
fun OrderItemList(
    orderItems: List<OrderItemData>,
    onOrderItemClick: (OrderItem) -> Unit,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { onRefresh() },
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(orderItems, key = { it.id.toString() }) { orderItem ->
                when {
                    accountViewModel.state.roleMatches(UserType.LineCook) -> {
                        LineCookOrderItemCard(
                            onClick = { onOrderItemClick(orderItem) },
                            orderItem = orderItem,
                        )
                    }
                    accountViewModel.state.roleMatches(UserType.Waiter) -> {
                        DeliveryWaiterOrderItemCard(
                            onClick = { onOrderItemClick(orderItem) },
                            orderItem = orderItem,
                        )
                    }
                }
            }
        }
    }
}
