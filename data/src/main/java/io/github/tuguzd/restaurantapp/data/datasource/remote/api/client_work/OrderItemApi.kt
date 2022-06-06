package io.github.tuguzd.restaurantapp.data.datasource.remote.api.client_work

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.client_work.order_item.OrderItemData
import retrofit2.http.*

interface OrderItemApi {
    @GET("order_items/all")
    suspend fun readAll(): BackendResponse<List<OrderItemData>>

    @GET("order_items/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<OrderItemData?>

    @POST("order_items/save")
    suspend fun save(@Body item: OrderItemData): BackendResponse<OrderItemData>

    @DELETE("order_items/delete/{id}")
    suspend fun delete(@Path("id") id: String)

    @DELETE("order_items/clear")
    suspend fun clear()
}
