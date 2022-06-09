package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.util

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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderViewModel

/**
 * Lazy list of provided [orders].
 */
@Composable
fun OrderList(
    orders: List<OrderData>,
    onOrderClick: (Order) -> Unit,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
    orderViewModel: OrderViewModel,
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
            items(orders, key = { it.id.toString() }) { order ->
                OrderCard(
                    order = order,
                    onClick = {
                        orderViewModel.updateOrderItems(order)
                        onOrderClick(order)
                    }
                )
            }
        }
    }
}
