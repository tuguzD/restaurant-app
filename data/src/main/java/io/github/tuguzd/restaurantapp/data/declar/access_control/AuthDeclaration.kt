package io.github.tuguzd.restaurantapp.data.declar.access_control

import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

interface AuthDeclaration {
    suspend fun auth(credentials: UserCredentialsData): Result<UserTokenData, Error>

    suspend fun register(credentials: UserCredentialsData): Result<UserTokenData, Error>

    suspend fun googleOAuth2(authCode: UserTokenData): Result<UserTokenData, Error>
}
