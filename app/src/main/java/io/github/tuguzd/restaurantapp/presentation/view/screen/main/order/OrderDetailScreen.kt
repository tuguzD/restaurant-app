package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemType
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme

/**
 * Application screen with information about provided [order].
 */
@Composable
fun OrderDetailScreen(
    order: Order,
    painter: Painter? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        val imageShape = MaterialTheme.shapes.medium.copy(
            topStart = ZeroCornerSize,
            topEnd = ZeroCornerSize,
        )

        Surface(tonalElevation = 5.dp, shape = imageShape) {
            Image(
                modifier = Modifier
                    .heightIn(min = 240.dp)
                    .fillMaxWidth(),
                painter = painter ?: ColorPainter(Color.Transparent),
                contentDescription = stringResource(R.string.order_picture),
            )
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            OrderProperty(
                name = stringResource(R.string.serviceItemPoint),
                value = order.serviceItemPoint?.name.toString(),
            )
            Divider()

            OrderProperty(
                name = stringResource(R.string.client_count),
                value = order.clientCount.toString(),
            )
            Divider()

            OrderProperty(
                name = stringResource(R.string.description),
                value = order.description.toString(),
            )
            Divider()

            OrderProperty(
                name = stringResource(R.string.order_time),
                value = order.datetimeCreate.toString(),
            )
            Divider()

            OrderProperty(
                name = stringResource(R.string.purchased),
                value = order.purchased.toString(),
            )
            Divider()
        }
    }
}

/**
 * Property of the component with provided [name] and [value].
 */
@Composable
fun OrderProperty(name: String, value: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = name,
        style = MaterialTheme.typography.labelMedium,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = value,
        style = MaterialTheme.typography.bodyLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun ComponentDetailsScreenPreview() {
    RestaurantAppTheme {
        val serviceItemPoint = ServiceItemPointData(
            name = "Table №7"
        )

        val order = OrderData(
            description = null,
            clientCount = 3,
            serviceItemPoint = serviceItemPoint
        )

        val menuItem = MenuItemData(
            type = MenuItemType.Cocktail,
            description = "Nice strawberry cocktail",
        )

        val mockOrder = OrderData(
            description = null,
            clientCount = 3,
            serviceItemPoint = serviceItemPoint,
            orderItems = List(5) {
                OrderItemData(
                    order = order,
                    menuItem = menuItem
                )
            }.toSet()
        )

        Surface {
            OrderDetailScreen(mockOrder)
        }
    }
}
