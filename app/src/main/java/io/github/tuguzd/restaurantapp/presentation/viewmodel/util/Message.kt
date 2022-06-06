package io.github.tuguzd.restaurantapp.presentation.viewmodel.util

import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId

interface MessageState<T : MessageKind> {
    val userMessages: List<UserMessage<out T>>
}

interface MessageKind

data class UserMessage<T : MessageKind>(val kind: T, val id: NanoId = randomNanoId())

enum class BackendErrorKind : MessageKind {
    ServerError,
    NetworkError,
    UnknownError,
}
