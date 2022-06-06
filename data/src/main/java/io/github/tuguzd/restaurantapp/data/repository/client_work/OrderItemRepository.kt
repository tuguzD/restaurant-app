package io.github.tuguzd.restaurantapp.data.repository.client_work

import io.github.tuguzd.restaurantapp.data.declar.client_work.OrderItemDeclaration
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class OrderItemRepository(
    private val dataSource: OrderItemDeclaration
) : OrderItemDeclaration {

    override suspend fun readAll():
        Result<List<OrderItemData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<OrderItemData?, Error> = dataSource.readById(id)

    override suspend fun save(item: OrderItemData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
