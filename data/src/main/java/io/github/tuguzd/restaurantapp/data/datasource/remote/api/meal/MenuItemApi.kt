package io.github.tuguzd.restaurantapp.data.datasource.remote.api.meal

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import retrofit2.http.*

interface MenuItemApi {
    @GET("menu_items/all")
    suspend fun readAll(): BackendResponse<List<MenuItemData>>

    @GET("menu_items/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<MenuItemData?>

    @POST("menu_items/save")
    suspend fun save(@Body item: MenuItemData): BackendResponse<MenuItemData>

    @DELETE("menu_items/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("menu_items/clear")
    suspend fun clear()
}
