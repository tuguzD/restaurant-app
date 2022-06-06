package io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import retrofit2.http.*

interface ServiceItemPointApi {
    @GET("service_item_points/all")
    suspend fun readAll(): BackendResponse<List<ServiceItemPointData>>

    @GET("service_item_points/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceItemPointData?>

    @POST("service_item_points/save")
    suspend fun save(@Body item: ServiceItemPointData): BackendResponse<ServiceItemPointData>

    @DELETE("service_item_points/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("service_item_points/clear")
    suspend fun clear()
}
