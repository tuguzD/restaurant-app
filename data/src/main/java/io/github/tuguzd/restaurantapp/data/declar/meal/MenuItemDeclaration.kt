package io.github.tuguzd.restaurantapp.data.declar.meal

import io.github.tuguzd.restaurantapp.data.declar.util.CrudDeclaration
import io.github.tuguzd.restaurantapp.domain.model.meal.menu_item.MenuItemData
import io.github.tuguzd.restaurantapp.domain.model.util.NanoId

interface MenuItemDeclaration : CrudDeclaration<NanoId, MenuItemData>
