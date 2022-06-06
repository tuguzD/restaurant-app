package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.organization

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization.ServiceItemPointApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.organization.ServiceItemPointDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteServiceItemPointDataSource(private val api: ServiceItemPointApi) :
    ServiceItemPointDeclaration {

    override suspend fun readAll(): Result<List<ServiceItemPointData>, Error> =
        withContext(Dispatchers.IO) { api.readAll() }.toResult()

    override suspend fun readById(id: NanoId): Result<ServiceItemPointData?, Error> =
        withContext(Dispatchers.IO) { api.readById(id = "$id") }.toResult()

    override suspend fun save(item: ServiceItemPointData): Result<Unit, Error> =
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
