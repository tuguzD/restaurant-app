package io.github.tuguzd.restaurantapp.presentation.view.navigation.main.order

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.DescribableDestination
import io.github.tuguzd.restaurantapp.presentation.view.screen.main.order.OrderScreen

/**
 * Describable navigation destinations
 * of [*Orders*][OrderScreen] main application destination.
 */
sealed class OrderScreenDestinations(override val route: String) : DescribableDestination {

    object OrderList : OrderScreenDestinations(route = "orderList") {
        override val description: String
            @Composable get() = stringResource(R.string.orders)
    }

    object OrderCreate : OrderScreenDestinations(route = "orderCreate") {
        override val description: String
            @Composable get() = stringResource(R.string.add_order)
    }

    object OrderDetail : OrderScreenDestinations(route = "order") {
        override val description: String
            @Composable get() = stringResource(R.string.order_detail)
    }

    object OrderItemDetail : OrderScreenDestinations(route = "orderItem") {
        override val description: String
            @Composable get() = stringResource(R.string.order_item_detail)
    }
}
