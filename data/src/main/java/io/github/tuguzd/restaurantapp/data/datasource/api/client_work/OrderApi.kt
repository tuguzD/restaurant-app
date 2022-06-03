package io.github.tuguzd.restaurantapp.data.datasource.api.client_work

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import retrofit2.http.*

interface OrderApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<OrderData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<OrderData?>

    @POST("save")
    suspend fun save(@Body item: OrderData): BackendResponse<OrderData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
