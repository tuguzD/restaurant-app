package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.repository.client_work.OrderRepository
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mu.KotlinLogging
import javax.inject.Inject

/**
 * Injectable view model with list of orders.
 */
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
) : ViewModel() {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private var _state by mutableStateOf(OrderState())
    val state get() = _state

    private var updateJob: Job? = null

    init {
        updateOrders()
    }

    fun updateOrders() {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            _state = state.copy(isUpdating = true)
            _state = when (val result = orderRepository.readAll()) {
                is Result.Error -> {
                    logger.error(result.throwable) { "Unknown error: ${result.error}" }
                    val message = UserMessage(OrderMessageKind.UnknownError)
                    val userMessages = state.userMessages + message
                    state.copy(userMessages = userMessages, isUpdating = false)
                }
                is Result.Success -> state.copy(
                    orders = result.data, isUpdating = false
                )
            }
        }
    }

    fun userMessageShown(messageId: NanoId) {
        val messages = state.userMessages.filterNot { it.id == messageId }
        _state = state.copy(userMessages = messages)
    }
}
