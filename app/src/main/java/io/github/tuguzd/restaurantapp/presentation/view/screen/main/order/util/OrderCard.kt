package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.Order
import io.github.tuguzd.restaurantapp.presentation.R

/**
 * [Card] composable with data of the provided [order]
 * and optional image provided by [painter].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(
    order: Order,
    painter: Painter? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    imageShape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit,
) {
    ElevatedCard(shape = shape, onClick = onClick) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                modifier = Modifier
                    .size(128.dp).clip(imageShape)
                    .run {
                        when (painter) {
                            null -> placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.fade(),
                            )
                            else -> this
                        }
                    },
                painter = painter ?: ColorPainter(Color.Transparent),
                contentDescription = painter?.let {
                    stringResource(R.string.order_picture)
                },
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                DisableSelection {
                    Text(
                        text = order.serviceItemPoint?.name +
                            "\nwith ${order.clientCount} guests",
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
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
