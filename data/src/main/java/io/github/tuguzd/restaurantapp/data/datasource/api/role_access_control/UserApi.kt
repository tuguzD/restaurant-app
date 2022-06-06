package io.github.tuguzd.restaurantapp.data.datasource.api.role_access_control

import io.github.tuguzd.restaurantapp.data.datasource.api.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Backend API for user management (requires no access token).
 */
interface UserApi {
    @GET("all")
    suspend fun readAll(): BackendResponse<List<UserData>>

    @GET("current")
    suspend fun current(): BackendResponse<UserData>

    @GET("id/{id}")
    suspend fun readById(@Path("id") id: String): BackendResponse<UserData?>

    @GET("username/{username}")
    suspend fun readByUsername(@Path("username") username: String): BackendResponse<UserData?>
}
