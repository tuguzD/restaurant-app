package io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import retrofit2.http.*

interface ServiceApi {
    @GET("services/all")
    suspend fun readAll(): BackendResponse<List<ServiceData>>

    @GET("services/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<ServiceData?>

    @POST("services/save")
    suspend fun save(@Body item: ServiceData): BackendResponse<ServiceData>

    @DELETE("services/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("services/clear")
    suspend fun clear()
}
