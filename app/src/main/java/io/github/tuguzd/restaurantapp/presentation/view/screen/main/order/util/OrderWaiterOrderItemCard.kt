package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util.OrderItemCard

@Composable
fun OrderWaiterOrderItemCard(
    onClick: () -> Unit,
    isWaiter: Boolean,
    orderItem: OrderItemData,
) {
    OrderItemCard(
        orderItem = orderItem,
        onClick = onClick,
        text = orderItem.description.toString()
    ) {
        if (isWaiter) {
            IconButton(
                onClick = {
//                var count = orderItem.itemCount
//                orderItem.copy(itemCount = --count)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription =
                    stringResource(R.string.minus_order_item_count)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${orderItem.itemCount}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    //
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription =
                    stringResource(R.string.plus_order_item_count)
                )
            }
        }
    }
}
