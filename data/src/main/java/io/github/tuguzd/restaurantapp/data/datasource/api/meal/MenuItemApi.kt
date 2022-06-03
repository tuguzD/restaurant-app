package io.github.tuguzd.restaurantapp.data.datasource.api.meal

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import retrofit2.http.*

interface MenuItemApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<MenuItemData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<MenuItemData?>

    @POST("save")
    suspend fun save(@Body item: MenuItemData): BackendResponse<MenuItemData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
