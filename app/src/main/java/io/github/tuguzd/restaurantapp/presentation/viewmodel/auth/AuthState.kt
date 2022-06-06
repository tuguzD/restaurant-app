package io.github.tuguzd.restaurantapp.presentation.viewmodel.auth

import io.github.tuguzd.restaurantapp.domain.util.checkPassword
import io.github.tuguzd.restaurantapp.domain.util.checkUsername
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.BackendErrorKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageState
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage

data class AuthState(
    val username: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    override val userMessages: List<UserMessage<out AuthMessageKind>> = listOf(),
) : MessageState<AuthMessageKind>

inline val AuthState.isValid: Boolean
    get() = checkUsername(username) && checkPassword(password)

sealed interface AuthMessageKind : MessageKind {
    data class Backend(val backendErrorKind: BackendErrorKind) : AuthMessageKind {
        companion object {
            fun server(): Backend = Backend(BackendErrorKind.ServerError)
            fun network(): Backend = Backend(BackendErrorKind.NetworkError)
            fun unknown(): Backend = Backend(BackendErrorKind.UnknownError)
        }
    }

    object NoGoogleId : AuthMessageKind
}
