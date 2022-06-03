package io.github.tuguzd.restaurantapp.data.datasource.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import retrofit2.http.*

interface ServiceItemPointApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<ServiceItemPointData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceItemPointData?>

    @POST("save")
    suspend fun save(@Body item: ServiceItemPointData): BackendResponse<ServiceItemPointData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
