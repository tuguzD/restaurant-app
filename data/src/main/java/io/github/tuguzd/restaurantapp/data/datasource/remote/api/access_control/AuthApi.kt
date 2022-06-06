package io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control

import io.github.tuguzd.restaurantapp.data.datasource.remote.util.BackendResponse
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.model.access_control.token.UserTokenData
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Backend API for authentication (requires no access token).
 */
interface AuthApi {
    @POST("auth")
    suspend fun auth(@Body credentials: UserCredentialsData):
        BackendResponse<UserTokenData>

    @POST("register")
    suspend fun register(@Body credentials: UserCredentialsData):
        BackendResponse<UserTokenData>

    @POST("oauth2/google")
    suspend fun googleOAuth2(@Body authCode: UserTokenData):
        BackendResponse<UserTokenData>
}
