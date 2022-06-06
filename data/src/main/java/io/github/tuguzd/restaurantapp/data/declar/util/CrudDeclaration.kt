package io.github.tuguzd.restaurantapp.data.declar.util

import io.github.tuguzd.restaurantapp.domain.model.util.feature.Identifiable
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result

interface CrudDeclaration<I, T : Identifiable<I>> {
    suspend fun readAll(): Result<List<T>, Error>

    suspend fun readById(id: I): Result<T?, Error>

    suspend fun save(item: T): Result<Unit, Error>

    suspend fun delete(id: I): Result<Unit, Error>

    suspend fun clear(): Result<Unit, Error>
}
