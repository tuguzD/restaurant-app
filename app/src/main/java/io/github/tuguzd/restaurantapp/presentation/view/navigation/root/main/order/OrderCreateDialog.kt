package io.github.tuguzd.restaurantapp.presentation.view.navigation.root.main.order

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme

/**
 * Application dialog for manual [component][OrderData] creation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCreateDialog(
    modifier: Modifier = Modifier,
    onOrderCreate: (OrderData) -> Unit,
    onNavigateUp: () -> Unit,
) {
    // TODO: Add `serviceItemPoint` dropdown
    var description by rememberSaveable { mutableStateOf("") }
    var clientCount by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = focusManager::clearFocus,
        ),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(R.string.add_order)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = clientCount,
                    onValueChange = { clientCount = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.client_count)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.description)) },
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = {
                    val order = OrderData(
                        id = randomNanoId(),
                        user = null,
                        serviceItemPoint = null,
                        description = description,
                        clientCount = clientCount.toInt(),
                    )
                    onOrderCreate(order)
                },
                enabled = kotlin.run {
                    clientCount.toIntOrNull() ?: return@run false
                    description.isNotEmpty()
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text(text = stringResource(R.string.add_order))
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun AddComponentDialogPreview() {
    RestaurantAppTheme {
        OrderCreateDialog(
            modifier = Modifier.fillMaxSize(),
            onOrderCreate = {},
            onNavigateUp = {},
        )
    }
}
