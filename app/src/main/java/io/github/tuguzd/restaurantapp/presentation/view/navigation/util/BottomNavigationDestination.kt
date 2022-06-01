package io.github.tuguzd.restaurantapp.presentation.view.navigation.util

import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents **bottom navigation** destinations for Compose Navigation.
 *
 * Provides [icon] for [bottom navigation item][NavigationBarItem].
 */
interface BottomNavigationDestination : DescribableDestination {
    val icon: ImageVector
}
