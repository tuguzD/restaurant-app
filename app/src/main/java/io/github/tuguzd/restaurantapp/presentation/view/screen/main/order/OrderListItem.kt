package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.presentation.R

/**
 * [Card] composable with data of the provided [order]
 * and optional image provided by [painter].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderListItem(
    order: Order,
    painter: Painter? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit,
) {
    Card(shape = shape, onClick = onClick) {
        Column {
            Surface(tonalElevation = 5.dp, shape = shape) {
                Image(
                    painter = painter ?: ColorPainter(Color.Transparent),
                    contentDescription = painter?.let {
                        stringResource(R.string.order_picture)
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(194.dp)
                        .fillMaxWidth(),
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                DisableSelection {
                    Text(
                        text = "${order.serviceItemPoint.name} " +
                            "with ${order.clientCount} guests",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = order.description.toString(),
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

// @Preview(name = "Light Mode")
// @Preview(
//    name = "Dark Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
// )
// @Composable
// private fun OrderListItemPreview() {
//    RestaurantAppTheme {
//        val serviceItemPoint = ServiceItemPointData(
//            name = "Table №7"
//        )
//
//        val order = OrderData(
//            description = null,
//            clientCount = 3,
//            serviceItemPoint = serviceItemPoint
//        )
//
//        val menuItem = MenuItemData(
//            type = MenuItemType.Cocktail,
//            description = "Nice strawberry cocktail",
//        )
//
//        val mockOrder = OrderData(
//            description = null,
//            clientCount = 3,
//            serviceItemPoint = serviceItemPoint,
//            orderItems = List(5) {
//                OrderItemData(
//                    order = order,
//                    menuItem = menuItem
//                )
//            }.toSet()
//        )
//
//        Surface {
//            OrderListItem(
//                order = mockOrder,
//                painter = painterResource(R.drawable.ic_launcher_background),
//                onClick = {},
//            )
//        }
//    }
// }
