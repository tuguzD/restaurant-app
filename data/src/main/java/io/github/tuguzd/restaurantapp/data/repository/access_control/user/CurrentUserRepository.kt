package io.github.tuguzd.restaurantapp.data.repository.access_control.user

import io.github.tuguzd.restaurantapp.domain.model.access_control.user.User
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.success

class CurrentUserRepository {
    private var currentUser: User? = null

    suspend fun getCurrentUser(): Result<User?, Error> = success(currentUser)

    suspend fun updateCurrentUser(currentUser: User?): Result<Unit, Error> {
        this.currentUser = currentUser
        return success(Unit)
    }
}
