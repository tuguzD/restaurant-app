package io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.user

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Backend API for user management (requires no access token).
 */
interface UserApi {
    @GET("users/all")
    suspend fun readAll(): BackendResponse<List<UserData>>

    @GET("users/current")
    suspend fun current(): BackendResponse<UserData>

    @GET("users/id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<UserData?>

    @GET("users/username/{username}")
    suspend fun readByUsername(@Path("username") username: String): BackendResponse<UserData?>
}
