package io.github.tuguzd.restaurantapp.presentation.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.user.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.client_work.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.meal.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization.*
import io.github.tuguzd.restaurantapp.data.repository.access_control.UserTokenRepository
import io.github.tuguzd.restaurantapp.domain.util.dataOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BackendModule {
    private const val backendBaseUrl = "http://192.168.0.110:8080/"

    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun providesConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    @Provides
    fun provideAuthInterceptorClient(tokenRepository: UserTokenRepository): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = runBlocking { tokenRepository.getToken() }.dataOrNull()?.token
                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @AuthRetrofit
    fun provideAuthRetrofit(converterFactory: Converter.Factory):
        Retrofit = retrofit(backendBaseUrl, converterFactory)

    @Provides
    @SimpleRetrofit
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit = retrofit(backendBaseUrl, converterFactory, authInterceptorClient)

    @Provides fun providesAuthAPI(
        @AuthRetrofit retrofit: Retrofit
    ): AuthApi = retrofit.create()

    @Provides fun providesUserAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): UserApi = retrofit.create()

    @Provides fun providesOrderAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): OrderApi = retrofit.create()

    @Provides fun providesOrderItemAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): OrderItemApi = retrofit.create()

    @Provides fun providesMenuAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): MenuApi = retrofit.create()

    @Provides fun providesMenuItemAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): MenuItemApi = retrofit.create()

    @Provides fun providesServiceAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): ServiceApi = retrofit.create()

    @Provides fun providesServiceItemAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): ServiceItemApi = retrofit.create()

    @Provides fun providesServiceItemPointAPI(
        @SimpleRetrofit retrofit: Retrofit
    ): ServiceItemPointApi = retrofit.create()

    @JvmStatic
    @Suppress("NOTHING_TO_INLINE")
    private inline fun retrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        client: OkHttpClient? = null,
    ): Retrofit =
        Retrofit.Builder()
            .apply { client?.let { client(it) } }
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
}
