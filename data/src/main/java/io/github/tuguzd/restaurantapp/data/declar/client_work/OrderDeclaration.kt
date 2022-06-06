package io.github.tuguzd.restaurantapp.data.declar.client_work

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.client_work.order.OrderData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId

interface OrderDeclaration : CrudDeclaration<NanoId, OrderData>
