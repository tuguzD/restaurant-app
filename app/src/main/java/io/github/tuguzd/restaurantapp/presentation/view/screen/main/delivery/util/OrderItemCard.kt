package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItemCard(
    painter: Painter? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    imageShape: Shape = MaterialTheme.shapes.medium,
    orderItem: OrderItemData,
    text: String,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    ElevatedCard(shape = shape, onClick = onClick) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
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

            DisableSelection {
                Column {
                    Text(
                        text = orderItem.menuItem.name,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        content()
                    }
                }
            }
        }
    }
}
