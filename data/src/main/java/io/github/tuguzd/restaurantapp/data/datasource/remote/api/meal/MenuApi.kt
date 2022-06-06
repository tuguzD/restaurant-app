package io.github.tuguzd.restaurantapp.data.datasource.remote.api.meal

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import retrofit2.http.*

interface MenuApi {
    @GET("menus/all")
    suspend fun readAll(): BackendResponse<List<MenuData>>

    @GET("menus/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<MenuData?>

    @POST("menus/save")
    suspend fun save(@Body item: MenuData): BackendResponse<MenuData>

    @DELETE("menus/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("menus/clear")
    suspend fun clear()
}
