package io.github.tuguzd.restaurantapp.presentation.view.navigation.util

import androidx.navigation.NavController
import io.github.tuguzd.restaurantapp.presentation.view.navigation.RootNavigationDestinations.Auth
import io.github.tuguzd.restaurantapp.presentation.view.navigation.RootNavigationDestinations.Main
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.MainScreen

/**
 * Navigate to the *Authentication* user flow.
 */
fun NavController.navigateAuth(popUpToDestination: Destination = Main) = navigate(Auth.route) {
    popUpTo(popUpToDestination.route) {
        inclusive = true
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

/**
 * Navigate to the [main screen][MainScreen].
 */
fun NavController.navigateMain(popUpToDestination: Destination = Auth) = navigate(Main.route) {
    popUpTo(popUpToDestination.route) {
        inclusive = true
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
