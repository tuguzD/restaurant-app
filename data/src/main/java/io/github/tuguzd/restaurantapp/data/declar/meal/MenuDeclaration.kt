package io.github.tuguzd.restaurantapp.data.declar.meal

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.meal.menu.MenuData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId

interface MenuDeclaration : CrudDeclaration<NanoId, MenuData>
