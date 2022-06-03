package io.github.tuguzd.restaurantapp.presentation.view.navigation.root.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.BrunchDining
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.BottomNavigationDestination

/**
 * Bottom navigation destinations of the application's [main screen][MainScreen].
 */
sealed class MainScreenDestinations(
    override val route: String,
    override val icon: ImageVector,
) : BottomNavigationDestination {

    object Orders : MainScreenDestinations(
        route = "orders",
        icon = Icons.Rounded.BrunchDining,
    ) {
        override val description: String
            @Composable get() = stringResource(R.string.orders)
    }

    object Delivery : MainScreenDestinations(
        route = "delivery",
        icon = Icons.Rounded.Timer,
    ) {
        override val description: String
            @Composable get() = stringResource(R.string.delivery)
    }

    object Account : MainScreenDestinations(
        route = "account",
        icon = Icons.Rounded.AccountCircle,
    ) {
        override val description: String
            @Composable get() = stringResource(R.string.account)
    }
}
