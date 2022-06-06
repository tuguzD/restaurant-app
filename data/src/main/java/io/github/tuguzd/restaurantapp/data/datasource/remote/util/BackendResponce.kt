package io.github.tuguzd.restaurantapp.data.datasource.remote.util

import com.haroldadmin.cnradapter.CompletableResponse
import com.haroldadmin.cnradapter.NetworkResponse
import io.github.tuguzd.restaurantapp.domain.util.Error
import io.github.tuguzd.restaurantapp.domain.util.Result
import io.github.tuguzd.restaurantapp.domain.util.error
import io.github.tuguzd.restaurantapp.domain.util.success
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

typealias BackendResponse<S> = NetworkResponse<S, Error>

typealias BackendCompletableResponse = CompletableResponse<Error>

/**
 * Create unknown error response to be handled bu application.
 */
fun makeUnknownError(message: String): BackendCompletableResponse {
    val error = Exception(message)
    val errorResponse = Response.error<Nothing>(500, "".toResponseBody())
    return NetworkResponse.UnknownError(error, errorResponse)
}

/**
 * Create successful response.
 */
fun makeSuccess(): BackendCompletableResponse {
    val response = Response.success(Unit)
    return NetworkResponse.Success(Unit, response)
}

/**
 * Converts [NetworkResponse] to data layer [Result].
 */
fun <S, E> NetworkResponse<S, E>.toResult(): Result<S, E> = when (this) {
    is NetworkResponse.Success -> success(body)
    is NetworkResponse.Error -> error(body, error)
}
