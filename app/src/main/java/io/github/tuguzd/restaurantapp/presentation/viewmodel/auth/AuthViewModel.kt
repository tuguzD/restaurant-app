package io.github.tuguzd.restaurantapp.presentation.viewmodel.auth

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.repository.access_control.AuthRepository
import io.github.tuguzd.restaurantapp.data.repository.access_control.UserTokenRepository
import io.github.tuguzd.restaurantapp.data.repository.access_control.user.CurrentUserRepository
import io.github.tuguzd.restaurantapp.data.repository.access_control.user.UserRepository
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.presentation.viewmodel.util.UserMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import mu.KotlinLogging
import javax.inject.Inject

/**
 * Injectable view model for user authentication.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val tokenRepository: UserTokenRepository,
    private val googleSignInClient: GoogleSignInClient,
    private val currentUserRepository: CurrentUserRepository,
) : ViewModel() {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    private var _state by mutableStateOf(AuthState())
    val state get() = _state

    fun updateUsername(username: String) {
        _state = state.copy(username = username)
    }

    fun updatePassword(password: String) {
        _state = state.copy(password = password)
    }

    fun updatePasswordVisible(passwordVisible: Boolean) {
        _state = state.copy(passwordVisible = passwordVisible)
    }

    fun updateIsLoggedIn(isLoggedIn: Boolean) {
        _state = state.copy(isLoggedIn = isLoggedIn)
    }

    fun userMessageShown(messageId: NanoId) {
        val messages = state.userMessages.filterNot { it.id == messageId }
        _state = state.copy(userMessages = messages)
    }

    private var authJob: Job? = null

    fun auth() {
        authJob?.cancel()
        authJob = viewModelScope.launch {
            _state = state.copy(isLoading = true)
            val credentials = UserCredentialsData(
                username = state.username,
                password = state.password,
            )
            authRepository.auth(credentials).handle { tokenData ->
                tokenRepository.setToken(tokenData)
                userRepository.current().handle {
                    currentUserRepository.updateCurrentUser(it)
                    _state = state.copy(isLoggedIn = true)
                }
            }
            _state = state.copy(isLoading = false)
        }
    }

    fun register() {
        authJob?.cancel()
        authJob = viewModelScope.launch {
            _state = state.copy(isLoading = true)
            val credentials = UserCredentialsData(
                username = state.username,
                password = state.password,
            )
            authRepository.register(credentials).handle { token ->
                tokenRepository.setToken(token)
                userRepository.current().handle {
                    currentUserRepository.updateCurrentUser(it)
                    _state = state.copy(isLoggedIn = true)
                }
            }
            _state = state.copy(isLoading = false)
        }
    }

    val googleSignInIntent: Intent
        get() = googleSignInClient.signInIntent

    fun googleOAuth2(account: GoogleSignInAccount) {
        val authCodeString = account.serverAuthCode ?: run {
            val newMessage = UserMessage(AuthMessageKind.NoGoogleId)
            val errorMessages = state.userMessages + newMessage
            _state = state.copy(userMessages = errorMessages)
            return
        }

        authJob?.cancel()
        authJob = viewModelScope.launch {
            _state = state.copy(isLoading = true)
            val authCode = UserTokenData(authCodeString)
            authRepository.googleOAuth2(authCode).handle { token ->
                tokenRepository.setToken(token)
                userRepository.current().handle {
                    currentUserRepository.updateCurrentUser(it)
                    _state = state.copy(isLoggedIn = true)
                }
            }
            _state = state.copy(isLoading = false)
        }
    }

    private inline fun <S, E> Result<S, E>.handle(onSuccess: (S) -> Unit): Unit =
        when (this) {
            is Result.Success -> onSuccess(data)
            is Result.Error -> {
                logger.error(throwable) { "Unknown error occurred" }
                val message = UserMessage(AuthMessageKind.Backend.unknown())
                val errorMessages = state.userMessages + message
                _state = state.copy(userMessages = errorMessages)
            }
        }
}
