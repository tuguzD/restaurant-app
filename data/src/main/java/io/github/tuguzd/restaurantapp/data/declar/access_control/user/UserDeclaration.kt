package io.github.tuguzd.restaurantapp.data.declar.access_control.user

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

interface UserDeclaration : CrudDeclaration<NanoId, UserData> {
    suspend fun current(): Result<UserData, Error>

    suspend fun readByUsername(username: String): Result<UserData?, Error>
}
