package io.github.tuguzd.restaurantapp.presentation.viewmodel.main.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.repository.access_control.UserTokenRepository
import io.github.tuguzd.restaurantapp.data.repository.access_control.user.CurrentUserRepository
import io.github.tuguzd.restaurantapp.data.repository.access_control.user.UserRepository
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.dataOrNull
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import mu.KotlinLogging
import javax.inject.Inject

/**
 * Injectable view model which holds information about current user.
 */
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val usersRepository: UserRepository,
    private val tokenRepository: UserTokenRepository,
    private val googleSignInClient: GoogleSignInClient,
    private val currentUserRepository: CurrentUserRepository,
) : ViewModel() {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private var _state by mutableStateOf(AccountState())
    val state get() = _state

    private var updateJob: Job? = null

    init {
        updateUser()
    }

    fun updateUser() {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            _state = state.copy(isLoading = true)
            tokenRepository.getToken().dataOrNull() ?: kotlin.run {
                signOut()
                return@launch
            }

            usersRepository.current().handle {
                currentUserRepository.updateCurrentUser(it)
                _state = state.copy(currentUser = it)
            }
            _state = state.copy(isLoading = false)
        }
    }

    fun signOut() {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            _state = state.copy(isLoading = true)
            tokenRepository.setToken(null)
            googleSignInClient.signOut().await()
            currentUserRepository.updateCurrentUser(currentUser = null)
            _state = state.copy(currentUser = null, isLoading = false)
        }
    }

    fun userMessageShown(messageId: NanoId) {
        val messages = state.userMessages.filterNot { it.id == messageId }
        _state = state.copy(userMessages = messages)
    }

    private inline fun <S, E> Result<S, E>.handle(onSuccess: (S) -> Unit): Unit =
        when (this) {
            is Result.Success -> onSuccess(data)
            is Result.Error -> {
                signOut()
                logger.error(throwable) { "Unknown error occurred" }
                val message = UserMessage(AccountMessageKind.Backend.unknown())
                val errorMessages = state.userMessages + message
                _state = state.copy(userMessages = errorMessages)
            }
        }
}
