package io.github.tuguzd.restaurantapp.data.declar.access_control

import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserToken
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

interface UserTokenDeclaration {
    suspend fun getToken(): Result<UserToken?, Error>

    suspend fun setToken(userToken: UserToken?): Result<Unit, Error>
}
