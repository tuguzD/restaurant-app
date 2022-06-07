package io.github.tuguzd.restaurantapp.presentation.viewmodel.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.presentation.view.navigation.util.Destination
import mu.KotlinLogging
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private var _state by mutableStateOf(MainState())
    val state get() = _state

    fun updateTitle(title: String) {
        _state = state.copy(title = title)
    }

    fun updateFilled(isFilled: Boolean) {
        _state = state.copy(isFilled = isFilled)
    }

    fun updateCurrentDestination(currentDestination: Destination) {
        _state = state.copy(currentDestination = currentDestination)
    }

    fun updateOnNavigateUpAction(onNavigateUpAction: () -> Unit) {
        _state = state.copy(onNavigateUpAction = onNavigateUpAction)
    }
}
