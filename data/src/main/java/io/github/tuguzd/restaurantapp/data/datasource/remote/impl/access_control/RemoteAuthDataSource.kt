package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.access_control

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.AuthApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.access_control.AuthDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteAuthDataSource(private val backendAuthAPI: AuthApi) :
    AuthDeclaration {

    override suspend fun auth(credentials: UserCredentialsData): Result<UserTokenData, Error> =
        withContext(Dispatchers.IO) { backendAuthAPI.auth(credentials) }.toResult()

    override suspend fun register(credentials: UserCredentialsData): Result<UserTokenData, Error> =
        withContext(Dispatchers.IO) { backendAuthAPI.register(credentials) }.toResult()

    override suspend fun googleOAuth2(authCode: UserTokenData): Result<UserTokenData, Error> =
        withContext(Dispatchers.IO) { backendAuthAPI.googleOAuth2(authCode) }.toResult()
}
