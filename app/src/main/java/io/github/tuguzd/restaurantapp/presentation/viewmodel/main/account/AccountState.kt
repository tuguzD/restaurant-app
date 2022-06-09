package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account

import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.BackendErrorKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageKind
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.MessageState
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage

data class AccountState(
    val currentUser: UserData? = null,
    val isLoading: Boolean = true,
    override val userMessages: List<UserMessage<out AccountMessageKind>> = listOf(),
) : MessageState<AccountMessageKind>

inline val AccountState.signedIn: Boolean
    get() = currentUser != null

fun AccountState.roleMatches(vararg roles: UserType): Boolean =
    roles.contains(currentUser?.userType)

sealed interface AccountMessageKind : MessageKind {
    data class Backend(val backendErrorKind: BackendErrorKind) : AccountMessageKind {
        companion object {
            fun server(): Backend = Backend(BackendErrorKind.ServerError)
            fun network(): Backend = Backend(BackendErrorKind.NetworkError)
            fun unknown(): Backend = Backend(BackendErrorKind.UnknownError)
        }
    }
}
