package io.github.tuguzd.restaurantapp.presentation.view.navigation.root.auth.signup

import android.content.res.Configuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.tuguzd.restaurantapp.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.auth.util.AuthScreen
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.auth.util.AuthScreenContent
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.auth.util.AuthVariant
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme
import io.github.tuguzd.restaurantapp.presentation.viewmodel.AuthViewModel

/**
 * Application user sign up screen.
 */
@Composable
fun SignUpScreen(
    onSignUp: (AuthVariant) -> Unit,
    onSignInNavigate: () -> Unit,
    viewModel: AuthViewModel = viewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) = AuthScreen(snackbarHostState) {
    AuthScreenContent(
        title = stringResource(R.string.sign_up),
        viewModel = viewModel,
        onAuth = onSignUp,
        alternativeDestinationDescription = stringResource(R.string.have_account),
        alternativeDestinationText = stringResource(R.string.sign_in),
        onAlternativeNavigate = onSignInNavigate,
    )
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun SignUpScreenPreview() {
    RestaurantAppTheme {
        SignUpScreen(onSignUp = {}, onSignInNavigate = {})
    }
}
