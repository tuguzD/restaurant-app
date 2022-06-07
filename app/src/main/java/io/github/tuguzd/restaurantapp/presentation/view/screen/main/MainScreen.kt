package io.github.tuguzd.restaurantapp.presentation.view.screen.main

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.MainScreenDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.account.AccountScreen
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.DeliveryScreen
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.OrderScreen
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.DestinationsNavigationBar
import io.github.tuguzd.restaurantapp.presentation.view.util.ToastDuration
import io.github.tuguzd.restaurantapp.presentation.view.util.showToast
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.navigationVisible

/**
 * Main screen of the application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val appName = stringResource(R.string.app_name)
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle(appName)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    LaunchedEffect(currentRoute) {
        val currentDestination = when (currentRoute) {
            Orders.route -> Orders
            Account.route -> Account
            Delivery.route -> Delivery
            else -> return@LaunchedEffect
        }
        mainViewModel.updateCurrentDestination(currentDestination)
    }

    val orderNavController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            MainScreenTopAppBar(
                mainViewModel = mainViewModel,
                orderNavController = orderNavController,
            )
        },
        bottomBar = {
            DestinationsNavigationBar(
                navController = navController,
                destinations = listOf(Orders, Delivery, Account),
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Orders.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Orders.route) {
                OrderScreen(
                    mainViewModel, navController = orderNavController,
                    snackbarHostState = snackbarHostState,
                )
            }
            composable(Delivery.route) {
                DeliveryScreen(mainViewModel)
            }
            composable(Account.route) account@{
                val user = accountViewModel.state.currentUser ?: return@account

                val context = LocalContext.current
                val toastText = stringResource(R.string.signed_out_success)
                AccountScreen(
                    user = user, mainViewModel = mainViewModel,
                    onSignOut = {
                        showToast(context, toastText, ToastDuration.Short)
                        accountViewModel.signOut()
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MainScreenTopAppBar(
    mainViewModel: MainViewModel,
    orderNavController: NavHostController,
) {
    val tonalElevation by animateDpAsState(if (mainViewModel.state.isFilled) 4.dp else 0.dp)

    Surface(tonalElevation = tonalElevation) {
        CenterAlignedTopAppBar(
            title = {
                AnimatedContent(targetState = mainViewModel.state.title) { title -> Text(title) }
            },
            navigationIcon = {
                if (mainViewModel.state.navigationVisible) {
                    IconButton(onClick = mainViewModel.state.onNavigateUpAction) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_up),
                        )
                    }
                }
            },
        )
    }
}
