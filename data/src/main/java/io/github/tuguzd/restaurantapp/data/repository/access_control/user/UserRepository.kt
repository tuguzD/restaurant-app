package io.github.tuguzd.restaurantapp.data.repository.access_control.user

import io.github.tuguzd.restaurantapp.data.declar.access_control.user.UserDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class UserRepository(
    private val dataSource: UserDeclaration
) : UserDeclaration {

    override suspend fun readAll():
        Result<List<UserData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<UserData?, Error> = dataSource.readById(id)

    override suspend fun current():
        Result<UserData, Error> = dataSource.current()

    override suspend fun readByUsername(username: String):
        Result<UserData?, Error> = dataSource.readByUsername(username)

    override suspend fun save(item: UserData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
