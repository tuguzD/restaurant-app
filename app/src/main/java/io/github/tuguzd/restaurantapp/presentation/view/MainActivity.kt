package io.github.tuguzd.restaurantapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import io.github.tuguzd.restaurantapp.presentation.view.navigation.RootNavigationDestinations
import io.github.tuguzd.restaurantapp.presentation.view.screen.RootScreen
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account.signedIn

/**
 * Entry point of the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            accountViewModel.state.isLoading
        }

        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme {
                val startDestination = when (accountViewModel.state.signedIn) {
                    true -> RootNavigationDestinations.Main
                    else -> RootNavigationDestinations.Auth
                }

                RootScreen(
                    startDestination = startDestination,
                    accountViewModel = accountViewModel,
                )
            }
        }
    }
}
