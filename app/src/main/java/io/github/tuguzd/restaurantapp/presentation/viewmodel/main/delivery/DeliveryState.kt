package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.delivery

import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageState
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage

data class DeliveryState(
    val orderItems: List<OrderItemData> = listOf(),
    val isUpdating: Boolean = true,
    override val userMessages: List<UserMessage<DeliveryMessageKind>> = listOf(),
) : MessageState<DeliveryMessageKind>

enum class DeliveryMessageKind : MessageKind {
    OrderItemReady,
    OrderItemDelivered,
    UnknownError,
}
