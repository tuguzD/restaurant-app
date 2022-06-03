package io.github.tuguzd.restaurantapp.data.datasource.api.client_work

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import retrofit2.http.*

interface OrderItemApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<OrderItemData>>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<OrderItemData?>

    @POST("save")
    suspend fun save(@Body item: OrderItemData): BackendResponse<OrderItemData>

    @DELETE("delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("clear")
    suspend fun clear()
}
