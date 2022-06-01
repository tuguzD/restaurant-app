package io.github.tuguzd.restaurantapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.util.RootScreen
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme { RootScreen() }
        }
    }
}
