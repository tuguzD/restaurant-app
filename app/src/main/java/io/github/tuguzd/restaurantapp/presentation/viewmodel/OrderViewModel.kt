package io.github.tuguzd.restaurantapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.tuguzd.restaurantapp.data.datasource.api.client_work.OrderApi
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Injectable view model with list of orders.
 */
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderApi: OrderApi,
//    private val orderRepository: RepositoryService<String, OrderData>,
) : ViewModel() {

    val orders: Flow<List<OrderData>> get() = flow { orderApi.readAll() }

    fun readById(id: String): Flow<OrderData> = flow { orderApi.readById(id) }

    suspend fun createOrder(order: OrderData): Flow<OrderData> = flow { orderApi.save(order) }
}
