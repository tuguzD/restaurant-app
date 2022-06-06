package io.github.tuguzd.restaurantapp.data.declar.organization

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.organization.service.ServiceData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId

interface ServiceDeclaration : CrudDeclaration<NanoId, ServiceData>
