package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.R

@Composable
fun LineCookOrderItemCard(
    onClick: () -> Unit,
    orderItem: OrderItemData,
) {
    val enabled = !orderItem.ready

    OrderItemCard(
        orderItem = orderItem,
        onClick = onClick,
        text = orderItem.description.toString(),
    ) {
        Button(
            enabled = enabled,
            onClick = {
                //
            },
        ) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(
                    if (enabled) R.string.mark_ready
                    else R.string.marked_ready
                )
            )
        }
    }
}
