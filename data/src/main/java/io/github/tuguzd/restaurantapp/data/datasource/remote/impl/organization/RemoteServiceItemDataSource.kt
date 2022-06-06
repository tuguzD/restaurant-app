package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.organization

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization.ServiceItemApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.organization.ServiceItemDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteServiceItemDataSource(private val api: ServiceItemApi) :
    ServiceItemDeclaration {

    override suspend fun readAll(): Result<List<ServiceItemData>, Error> =
        withContext(Dispatchers.IO) { api.readAll() }.toResult()

    override suspend fun readById(id: NanoId): Result<ServiceItemData?, Error> =
        withContext(Dispatchers.IO) { api.readById(id = "$id") }.toResult()

    override suspend fun save(item: ServiceItemData): Result<Unit, Error> =
        withContext(Dispatchers.IO) {
            api.save(item)
            success(Unit)
        }

    override suspend fun delete(id: NanoId): Result<Unit, Error> =
        withContext(Dispatchers.IO) {
            api.delete(id = "$id")
            success(Unit)
        }

    override suspend fun clear(): Result<Unit, Error> =
        withContext(Dispatchers.IO) {
            api.clear()
            success(Unit)
        }
}
