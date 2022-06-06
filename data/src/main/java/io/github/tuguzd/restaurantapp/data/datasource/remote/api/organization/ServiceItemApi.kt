package io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item.ServiceItemData
import retrofit2.http.*

interface ServiceItemApi {
    @GET("service_items/all")
    suspend fun readAll(): BackendResponse<List<ServiceItemData>>

    @GET("service_items/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceItemData?>

    @POST("service_items/save")
    suspend fun save(@Body item: ServiceItemData): BackendResponse<ServiceItemData>

    @DELETE("service_items/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("service_items/clear")
    suspend fun clear()
}
