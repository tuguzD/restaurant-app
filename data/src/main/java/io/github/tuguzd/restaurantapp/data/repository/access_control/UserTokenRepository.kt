package io.github.tuguzd.restaurantapp.data.repository.access_control

import io.github.tuguzd.restaurantapp.data.declar.access_control.UserTokenDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserToken
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class UserTokenRepository(
    private val dataSource: UserTokenDeclaration
) : UserTokenDeclaration {

    override suspend fun getToken(): Result<UserToken?, Error> = dataSource.getToken()

    override suspend fun setToken(userToken: UserToken?): Result<Unit, Error> =
        dataSource.setToken(userToken)
}
