package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.order

import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageState
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage

data class OrderState(
    val orders: List<OrderData> = listOf(),
    val orderItems: List<OrderItemData> = listOf(),
    val isUpdating: Boolean = true,
    override val userMessages: List<UserMessage<OrderMessageKind>> = listOf(),
) : MessageState<OrderMessageKind>

enum class OrderMessageKind : MessageKind {
    OrderAdded,
    OrderDeleted,
    OrderPurchased,
    UnknownError,
}
