package io.github.tuguzd.restaurantapp.data.repository.meal

import io.github.tuguzd.restaurantapp.data.declar.meal.MenuDeclaration
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class MenuRepository(
    private val dataSource: MenuDeclaration
) : MenuDeclaration {

    override suspend fun readAll():
        Result<List<MenuData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<MenuData?, Error> = dataSource.readById(id)

    override suspend fun save(item: MenuData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
