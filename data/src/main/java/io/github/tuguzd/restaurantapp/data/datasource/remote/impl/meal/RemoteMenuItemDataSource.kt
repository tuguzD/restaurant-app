package io.github.tuguzd.restaurantapp.data.datasource.remote.impl.meal

import io.github.tuguzd.restaurantapp.data.datasource.remote.api.meal.MenuItemApi
import io.github.tuguzd.restaurantapp.data.datasource.remote.util.toResult
import io.github.tuguzd.restaurantapp.data.declar.meal.MenuItemDeclaration
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteMenuItemDataSource(private val api: MenuItemApi) :
    MenuItemDeclaration {

    override suspend fun readAll(): Result<List<MenuItemData>, Error> =
        withContext(Dispatchers.IO) { api.readAll() }.toResult()

    override suspend fun readById(id: NanoId): Result<MenuItemData?, Error> =
        withContext(Dispatchers.IO) { api.readById(id = "$id") }.toResult()

    override suspend fun save(item: MenuItemData): Result<Unit, Error> =
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
