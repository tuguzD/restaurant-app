package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType

/**
 * Lazy list of provided [orders].
 */
@Composable
fun OrderList(
    orders: List<Order>,
    onOrderClick: (Order) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        state = state,
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(orders, key = { it.id }) { order ->
            OrderListItem(
                order = order,
                onClick = { onOrderClick(order) }
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun OrderListPreview() {
    val order = OrderData(
        description = null,
        clientCount = 3,
    )

    val menuItem = MenuItemData(
        type = MenuItemType.Cocktail,
        description = "Nice strawberry cocktail",
    )

    val orders = List(4) {
        OrderData(
            description = null,
            clientCount = 3,
            orderItems = setOf(
                OrderItemData(
                    order = order,
                    menuItem = menuItem
                ),
                OrderItemData(
                    order = order,
                    menuItem = menuItem
                )
            )
        )
    }

    Surface {
        OrderList(
            orders = orders,
            onOrderClick = {},
        )
    }
}
