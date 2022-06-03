package io.github.tuguzd.restaurantapp.data.datasource.api.meal

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import retrofit2.http.*

interface MenuApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<MenuData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<MenuData?>

    @POST("save")
    suspend fun save(@Body item: MenuData): BackendResponse<MenuData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
