package io.github.tuguzd.restaurantapp.presentation.viewmodel.main

import io.github.tuguzd.restaurantapp.presentation.view.navigation.main.MainScreenDestinations
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.Destination

data class MainState(
    val title: String = "",
    val isFilled: Boolean = false,
    val currentDestination: Destination = MainScreenDestinations.Orders,
    val onNavigateUpAction: () -> Unit = {},
)

inline val MainState.favoritesVisible: Boolean
    get() = currentDestination == MainScreenDestinations.Orders

inline val MainState.searchVisible: Boolean
    get() = favoritesVisible

inline val MainState.navigationVisible: Boolean
    get() = currentDestination !is MainScreenDestinations
