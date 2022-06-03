package io.github.tuguzd.restaurantapp.presentation.view.screen.main.delivery

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme

@Composable
fun DeliveryScreen(onTitleChanged: (String) -> Unit) {
    val appName = stringResource(R.string.app_name)
    SideEffect { onTitleChanged(appName) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.coming_soon),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun DeliveryScreenPreview() {
    RestaurantAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            DeliveryScreen(onTitleChanged = {})
        }
    }
}
