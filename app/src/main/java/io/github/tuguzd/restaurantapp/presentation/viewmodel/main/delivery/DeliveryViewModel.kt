package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.delivery

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.repository.client_work.OrderItemRepository
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mu.KotlinLogging
import javax.inject.Inject

/**
 * Injectable view model with list of order items.
 */
@HiltViewModel
class DeliveryViewModel @Inject constructor(
    private val orderItemRepository: OrderItemRepository,
) : ViewModel() {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private var _state by mutableStateOf(DeliveryState())
    val state get() = _state

    private var updateJob: Job? = null

    init {
        updateOrderItems()
    }

    fun updateOrderItems(order: OrderData? = null) {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            _state = state.copy(isUpdating = true)
            _state = when (val result = orderItemRepository.readAll()) {
                is Result.Error -> {
                    logger.error(result.throwable) { "Unknown error: ${result.error}" }
                    val message = UserMessage(DeliveryMessageKind.UnknownError)
                    val userMessages = state.userMessages + message
                    state.copy(userMessages = userMessages, isUpdating = false)
                }
                is Result.Success -> {
                    val data =
                        if (order == null) result.data
                        else result.data.filter { it.order.id == order.id }

                    state.copy(orderItems = data, isUpdating = false)
                }
            }
        }
    }

    fun userMessageShown(messageId: NanoId) {
        val messages = state.userMessages.filterNot { it.id == messageId }
        _state = state.copy(userMessages = messages)
    }
}
