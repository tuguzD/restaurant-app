package io.github.tuguzd.restaurantapp.data.repository.client_work

import io.github.tuguzd.restaurantapp.data.declar.client_work.OrderDeclaration
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class OrderRepository(
    private val dataSource: OrderDeclaration
) : OrderDeclaration {

    override suspend fun readAll():
        Result<List<OrderData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<OrderData?, Error> = dataSource.readById(id)

    override suspend fun save(item: OrderData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
