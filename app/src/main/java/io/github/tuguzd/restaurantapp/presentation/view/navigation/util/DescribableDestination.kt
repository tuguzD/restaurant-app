package io.github.tuguzd.restaurantapp.presentation.view.navigation.util

import androidx.compose.runtime.Composable

/**
 * Represents destinations for Compose Navigation that can be described.
 */
interface DescribableDestination : Destination {
    @get:Composable
    val description: String
}
