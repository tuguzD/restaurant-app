package io.github.tuguzd.restaurantapp.data.datasource.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import retrofit2.http.*

interface ServiceItemApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<ServiceItemData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceItemData?>

    @POST("save")
    suspend fun save(@Body item: ServiceItemData): BackendResponse<ServiceItemData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
