package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItem
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.EntityProperty
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel

@Composable
fun OrderItemDetailScreen(
    orderItem: OrderItem,
    painter: Painter? = null,
    mainViewModel: MainViewModel,
) {
    val title = stringResource(R.string.order_item_detail)
    SideEffect {
        mainViewModel.updateTitle(title)
        mainViewModel.updateFilled(isFilled = false)
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        val imageShape = MaterialTheme.shapes.medium.copy(
            topStart = ZeroCornerSize,
            topEnd = ZeroCornerSize,
        )
        Surface(tonalElevation = 5.dp, shape = imageShape) {
            Image(
                modifier = Modifier.heightIn(min = 240.dp).fillMaxWidth(),
                painter = painter ?: ColorPainter(Color.Transparent),
                contentDescription = stringResource(R.string.order_picture),
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
        ) {
            EntityProperty(
                name = stringResource(R.string.menu_item_name),
                value = orderItem.menuItem.name
            )
            Divider()

            orderItem.menuItem.description?.let {
                EntityProperty(
                    name = stringResource(R.string.order_item_desc),
                    value = it
                )
                Divider()
            }

            EntityProperty(
                name = stringResource(R.string.menu_item_type),
                value = orderItem.menuItem.menuItemType.toString()
            )
            Divider()

            EntityProperty(
                name = stringResource(R.string.order_item_count),
                value = orderItem.itemCount.toString()
            )
            Divider()
        }
    }
}
