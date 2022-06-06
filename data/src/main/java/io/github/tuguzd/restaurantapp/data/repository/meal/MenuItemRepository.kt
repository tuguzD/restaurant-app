package io.github.tuguzd.restaurantapp.data.repository.meal

import io.github.tuguzd.restaurantapp.data.declar.meal.MenuItemDeclaration
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

class MenuItemRepository(
    private val dataSource: MenuItemDeclaration
) : MenuItemDeclaration {

    override suspend fun readAll():
        Result<List<MenuItemData>, Error> = dataSource.readAll()

    override suspend fun readById(id: NanoId):
        Result<MenuItemData?, Error> = dataSource.readById(id)

    override suspend fun save(item: MenuItemData):
        Result<Unit, Error> = dataSource.save(item)

    override suspend fun delete(id: NanoId):
        Result<Unit, Error> = dataSource.delete(id)

    override suspend fun clear():
        Result<Unit, Error> = dataSource.clear()
}
