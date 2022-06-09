package io.github.tuguzd.restaurantapp.presentation.view.navigation.main.delivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.DescribableDestination
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery.DeliveryScreen

/**
 * Describable navigation destinations
 * of [*Delivery*][DeliveryScreen] main application destination.
 */
sealed class DeliveryScreenDestinations(override val route: String) : DescribableDestination {

    object DeliveryList : DeliveryScreenDestinations(route = "deliveryList") {
        override val description: String
            @Composable get() = stringResource(R.string.delivery)
    }

    object DeliveryDetail : DeliveryScreenDestinations(route = "delivery") {
        override val description: String
            @Composable get() = stringResource(R.string.order_item_detail)
    }
}
