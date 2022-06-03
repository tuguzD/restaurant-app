package io.github.tuguzd.restaurantapp.data.datasource.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import retrofit2.http.*

interface ServiceApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<ServiceData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceData?>

    @POST("save")
    suspend fun save(@Body item: ServiceData): BackendResponse<ServiceData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
