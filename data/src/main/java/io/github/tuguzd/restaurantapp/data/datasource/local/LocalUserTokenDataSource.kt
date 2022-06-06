package io.github.tuguzd.restaurantapp.data.datasource.local

import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import io.github.tuguzd.restaurantapp.data.declar.access_control.UserTokenDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserToken
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.toResult

class LocalUserTokenDataSource(private val sharedPreferences: EncryptedSharedPreferences) :
    UserTokenDeclaration {

    override suspend fun getToken(): Result<UserToken?, Error> =
        runCatching {
            sharedPreferences.getString("user_token", null)?.let { UserTokenData(it) }
        }.toResult()

    override suspend fun setToken(userToken: UserToken?): Result<Unit, Error> =
        runCatching {
            sharedPreferences.edit {
                putString("user_token", userToken?.token)
            }
        }.toResult()
}
