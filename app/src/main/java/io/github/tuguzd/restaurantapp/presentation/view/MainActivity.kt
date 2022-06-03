package io.github.tuguzd.restaurantapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.tuguzd.restaurantapp.presentation.view.screen.RootScreen
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme

/**
 * Entry point of the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme { RootScreen() }
        }
    }
}
