package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.R

@Composable
fun DeliveryWaiterOrderItemCard(
    onClick: () -> Unit,
    orderItem: OrderItemData,
) {
    val icon: ImageVector = when {
        !orderItem.ready -> Icons.Rounded.Error
        else -> Icons.Rounded.CheckCircle
    }
    val text: String = stringResource(
        when {
            !orderItem.ready -> R.string.not_ready
            else -> when {
                !orderItem.delivered -> R.string.mark_delivered
                else -> R.string.marked_delivered
            }
        }
    )

    OrderItemCard(
        orderItem = orderItem,
        onClick = onClick,
        text = orderItem.description.toString(),
    ) {
        Button(
            enabled = !orderItem.delivered && orderItem.ready,
            onClick = {
                //
            },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text)
        }
    }
}
