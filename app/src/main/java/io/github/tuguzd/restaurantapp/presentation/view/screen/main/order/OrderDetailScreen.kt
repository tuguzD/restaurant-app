package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

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
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.presentation.R

/**
 * Application screen with information about provided [order].
 */
@Composable
fun OrderDetailScreen(
    order: Order,
    painter: Painter? = null
) {
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
            modifier = Modifier.padding(8.dp).fillMaxSize(),
        ) {
            OrderProperty(
                name = stringResource(R.string.serviceItemPoint),
                value = order.serviceItemPoint.name,
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
                value = order.datetimeCreate,
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
