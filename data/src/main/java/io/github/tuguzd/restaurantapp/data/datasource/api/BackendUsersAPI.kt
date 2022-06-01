package io.github.tuguzd.restaurantapp.data.datasource.api

import io.github.tuguzd.restaurantapp.domain.model.role_access_control.user.UserData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Backend API for user management (requires no access token).
 */
interface BackendUsersAPI {
    @GET("all")
    suspend fun getAll(): BackendResponse<List<UserData>>

    @GET("current")
    suspend fun current(): BackendResponse<UserData>

    @GET("id/{id}")
    suspend fun findById(@Path("id") id: String): BackendResponse<UserData?>

    @GET("username/{username}")
    suspend fun findByUsername(@Path("username") username: String): BackendResponse<UserData?>
}
