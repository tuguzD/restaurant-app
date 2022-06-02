package io.github.tuguzd.restaurantapp.presentation.view.navigation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.RootNavigationDestinations.*
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.auth.util.authGraph
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.main.MainScreen
import io.github.tuguzd.restaurantapp.presentation.view.navigation.root.splash.SplashScreen
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.navigateAuth
import io.github.tuguzd.restaurantapp.presentation.viewmodel.AccountViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

/**
 * Root screen of the PC Builder application.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    accountViewModel: AccountViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold { padding ->
        NavHost(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            navController = navController,
            startDestination = Splash.route,
        ) {
            composable(Splash.route) {
                SplashScreen(accountViewModel, navController)
            }
            composable(Main.route) {
                val onSignOut: () -> Unit = {
                    navController.navigateAuth()
                    coroutineScope.launch { accountViewModel.signOut() }
                }
                MainScreen(onSignOut, accountViewModel)
            }
            authGraph(navController, authViewModel, accountViewModel)
        }
    }
}
