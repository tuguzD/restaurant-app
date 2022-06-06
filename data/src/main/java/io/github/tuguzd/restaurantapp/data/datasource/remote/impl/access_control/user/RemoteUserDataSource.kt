package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.access_control.user

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.user.UserApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.access_control.user.UserDeclaration
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteUserDataSource(private val userAPI: UserApi) :
    UserDeclaration {

    override suspend fun readAll(): Result<List<UserData>, Error> =
        withContext(Dispatchers.IO) { userAPI.readAll() }.toResult()

    override suspend fun current(): Result<UserData, Error> =
        withContext(Dispatchers.IO) { userAPI.current() }.toResult()

    override suspend fun readById(id: NanoId): Result<UserData?, Error> =
        withContext(Dispatchers.IO) { userAPI.readById(id = "$id") }.toResult()

    override suspend fun save(item: UserData): Result<Unit, Error> =
        withContext(Dispatchers.IO) { error("Not yet implemented") } // TODO

    override suspend fun delete(id: NanoId): Result<Unit, Error> =
        withContext(Dispatchers.IO) { error("Not yet implemented") } // TODO

    override suspend fun clear(): Result<Unit, Error> =
        withContext(Dispatchers.IO) { error("Not yet implemented") } // TODO

    override suspend fun readByUsername(username: String): Result<UserData?, Error> =
        withContext(Dispatchers.IO) { userAPI.readByUsername(username) }.toResult()
}
