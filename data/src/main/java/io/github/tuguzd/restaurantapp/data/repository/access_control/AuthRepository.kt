package io.github.tuguzd.restaurantapp.data.repository.access_control

import io.github.tuguzd.restaurantapp.data.declar.access_control.AuthDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class AuthRepository(
    private val dataSource: AuthDeclaration
) : AuthDeclaration {

    override suspend fun auth(credentials: UserCredentialsData):
        Result<UserTokenData, Error> = dataSource.auth(credentials)

    override suspend fun register(credentials: UserCredentialsData):
        Result<UserTokenData, Error> = dataSource.register(credentials)

    override suspend fun googleOAuth2(authCode: UserTokenData):
        Result<UserTokenData, Error> = dataSource.googleOAuth2(authCode)
}
