package io.github.tuguzd.restaurantapp.data.repository.organization

import io.github.tuguzd.restaurantapp.data.declar.organization.ServiceItemDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class ServiceItemRepository(
    private val dataSource: ServiceItemDeclaration
) : ServiceItemDeclaration {

    override suspend fun readAll():
        Result<List<ServiceItemData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<ServiceItemData?, Error> = dataSource.readById(id)

    override suspend fun save(item: ServiceItemData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
