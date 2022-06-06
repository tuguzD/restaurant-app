package io.github.tuguzd.restaurantapp.data.repository.organization

import io.github.tuguzd.restaurantapp.data.declar.organization.ServiceDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class ServiceRepository(
    private val dataSource: ServiceDeclaration
) : ServiceDeclaration {

    override suspend fun readAll():
        Result<List<ServiceData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<ServiceData?, Error> = dataSource.readById(id)

    override suspend fun save(item: ServiceData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
