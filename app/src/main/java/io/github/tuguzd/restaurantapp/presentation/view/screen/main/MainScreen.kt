package io.github.tuguzd.restaurantapp.presentation.view.screen.main

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.MainScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.account.AccountScreen
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.DeliveryScreen
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.OrdersScreen
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.DestinationsNavigationBar
import io.github.tuguzd.restaurantapp.presentation.view.util.ToastDuration
import io.github.tuguzd.restaurantapp.presentation.view.util.collectAsStateLifecycleAware
import io.github.tuguzd.restaurantapp.presentation.view.util.showToast
import io.github.tuguzd.restaurantapp.presentation.viewmodel.AccountViewModel

/**
 * Main screen of the application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onSignOut: () -> Unit,
    accountViewModel: AccountViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val appName = stringResource(R.string.app_name)

    var titleText by rememberSaveable { mutableStateOf(appName) }
    var showSearch by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            MainScreenTopAppBar(
                titleText = titleText,
                showSearch = showSearch,
                onSearchClick = { /* TODO */ },
            )
        },
        bottomBar = {
            DestinationsNavigationBar(
                navController = navController,
                destinations = listOf(Orders, Delivery, Account),
                onDestinationNavigate = { destination ->
                    showSearch = when (destination) {
                        Orders -> true
                        else -> false
                    }
                }
            )
        },
    ) { padding ->
        val onTitleChanged: (String) -> Unit = { titleText = it }

        NavHost(
            navController = navController,
            startDestination = Orders.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Orders.route) {
                OrdersScreen(onTitleChanged)
            }
            composable(Delivery.route) {
                DeliveryScreen(onTitleChanged)
            }
            composable(Account.route) account@{
                val currentUser by accountViewModel.currentUser.collectAsStateLifecycleAware()
                val user = currentUser ?: kotlin.run {
                    onSignOut()
                    return@account
                }

                val context = LocalContext.current
                val toastText = stringResource(R.string.signed_out_success)
                AccountScreen(
                    user = user,
                    onTitleChanged = onTitleChanged,
                    onSignOut = {
                        showToast(context, toastText, ToastDuration.Short)
                        onSignOut()
                    },
                )
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
private fun MainScreenPreview() {
    RestaurantAppTheme {
        MainScreen(onSignOut = {})
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MainScreenTopAppBar(
    titleText: String,
    showSearch: Boolean,
    onSearchClick: () -> Unit,
) {
    Surface(tonalElevation = 2.dp) {
        CenterAlignedTopAppBar(
            title = {
                AnimatedContent(targetState = titleText) { text -> Text(text) }
            },
            actions = {
                AnimatedVisibility(visible = showSearch) {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = stringResource(R.string.search_something),
                        )
                    }
                }
            },
        )
    }
}
