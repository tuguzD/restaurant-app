package io.github.tuguzd.restaurantapp.presentation.view.screen.main.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order.OrderViewModel

/**
 * Application dialog for manual [component][OrderData] creation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCreateScreen(
    mainViewModel: MainViewModel,
    orderViewModel: OrderViewModel,
    navController: NavHostController,
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    val title = stringResource(R.string.add_order)
    SideEffect {
        mainViewModel.updateTitle(title)
    }

    // TODO: Add `serviceItemPoint` dropdown
    var description by rememberSaveable { mutableStateOf("") }
    var clientCount by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.fillMaxSize().clickable(
            onClick = focusManager::clearFocus,
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
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
                    orderViewModel.createOrder(
                        OrderData(
                            creator = requireNotNull(accountViewModel.state.currentUser),
                            clientCount = clientCount.toInt(),
                            description = description,
                        )
                    )
//                    orderViewModel.updateOrders()
                    navController.navigateUp()
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
