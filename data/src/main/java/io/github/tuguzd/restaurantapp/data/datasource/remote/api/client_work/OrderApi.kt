package io.github.tuguzd.restaurantapp.data.datasource.remote.api.client_work

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import retrofit2.http.*

interface OrderApi {
    @GET("orders/all")
    suspend fun readAll(): BackendResponse<List<OrderData>>

    @GET("orders/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<OrderData?>

    @POST("orders/save")
    suspend fun save(@Body item: OrderData)

    @DELETE("orders/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("orders/clear")
    suspend fun clear()
}
