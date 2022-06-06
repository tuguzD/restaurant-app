package io.github.tuguzd.restaurantapp.data.declar.organization

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service_item_point.ServiceItemPointData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId

interface ServiceItemPointDeclaration : CrudDeclaration<NanoId, ServiceItemPointData>
