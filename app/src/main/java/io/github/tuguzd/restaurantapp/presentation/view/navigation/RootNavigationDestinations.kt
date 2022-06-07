package io.github.tuguzd.restaurantapp.presentation.view.navigation

import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.Destination
import io.github.tuguzd.restaurantapp.presentation.view.screen.RootScreen

/**
 * Navigation destinations of the application's [root screen][RootScreen].
 */
sealed class RootNavigationDestinations(override val route: String) : Destination {
    object Main : RootNavigationDestinations(route = "main")

    /**
     * Navigation destinations that represent *Authentication* user flow.
     */
    sealed class Auth(override val route: String) : RootNavigationDestinations(route) {
        companion object Root : Auth(route = "auth")

        object SignIn : Auth(route = "signIn")

        object SignUp : Auth(route = "signUp")
    }
}
