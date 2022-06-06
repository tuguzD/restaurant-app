package io.github.tuguzd.restaurantapp.presentation.viewmodel

import android.content.Intent
import androidx.annotation.CheckResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.datasource.api.util.AuthApi
import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendCompletableResponse
import io.github.tuguzd.restaurantapp.data.datasource.api.util.makeUnknownError
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Injectable view model for user authentication.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authAPI: AuthApi,
    private val sharedPreferences: EncryptedSharedPreferences,
    private val googleSignInClient: GoogleSignInClient,
) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    @CheckResult
    suspend fun auth(credentials: UserCredentialsData): BackendCompletableResponse {
        val userToken = withContext(Dispatchers.IO) {
            authAPI.auth(credentials)
        }
        return when (userToken) {
            is NetworkResponse.Success -> {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit {
                        putString("access_token", userToken.body.token)
                    }
                }
                NetworkResponse.Success(Unit, userToken.response)
            }
            is NetworkResponse.Error -> {
                @Suppress("UNCHECKED_CAST")
                userToken as BackendCompletableResponse
            }
        }
    }

    @CheckResult
    suspend fun register(user: UserCredentialsData): BackendCompletableResponse {
        val userToken = withContext(Dispatchers.IO) {
            authAPI.register(user)
        }
        return when (userToken) {
            is NetworkResponse.Success -> {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit {
                        putString("access_token", userToken.body.token)
                    }
                }
                NetworkResponse.Success(Unit, userToken.response)
            }
            is NetworkResponse.Error -> {
                @Suppress("UNCHECKED_CAST")
                userToken as BackendCompletableResponse
            }
        }
    }

    @get:CheckResult
    val googleSignInIntent: Intent
        get() = googleSignInClient.signInIntent

    @CheckResult
    suspend fun googleOAuth2(account: GoogleSignInAccount): BackendCompletableResponse {
        val authCodeString = account.serverAuthCode
            ?: return makeUnknownError("Cannot retrieve id from Google account")
        val authCode = UserTokenData(authCodeString)

        val userToken = withContext(Dispatchers.IO) {
            authAPI.googleOAuth2(authCode)
        }
        return when (userToken) {
            is NetworkResponse.Success -> {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit {
                        putString("access_token", userToken.body.token)
                    }
                }
                NetworkResponse.Success(Unit, userToken.response)
            }
            is NetworkResponse.Error -> {
                @Suppress("UNCHECKED_CAST")
                userToken as BackendCompletableResponse
            }
        }
    }
}
