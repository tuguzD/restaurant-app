package io.github.tuguzd.restaurantapp.presentation.view.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.haroldadmin.cnradapter.NetworkResponse
import io.github.tuguzd.restaurantapp.presentation.view.navigation.RootNavigationDestinations.Splash
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.navigateAuth
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.navigateMain
import io.github.tuguzd.restaurantapp.presentation.viewmodel.AccountViewModel

/**
 * Splash screen of the application.
 */
@Composable
fun SplashScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffect(Unit) {
        when (accountViewModel.updateUser()) {
            is NetworkResponse.Success -> when (accountViewModel.currentUser.value) {
                null -> navController.navigateAuth(Splash)
                else -> navController.navigateMain(Splash)
            }
            // TODO: add normal error handling
            else -> navController.navigateAuth(Splash)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {}
}
