package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.client_work

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.client_work.OrderApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.client_work.OrderDeclaration
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteOrderDataSource(private val api: OrderApi) :
    OrderDeclaration {

    override suspend fun readAll(): Result<List<OrderData>, Error> =
        withContext(Dispatchers.IO) { api.readAll() }.toResult()

    override suspend fun readById(id: NanoId): Result<OrderData?, Error> =
        withContext(Dispatchers.IO) { api.readById(id = "$id") }.toResult()

    override suspend fun save(item: OrderData): Result<Unit, Error> =
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
