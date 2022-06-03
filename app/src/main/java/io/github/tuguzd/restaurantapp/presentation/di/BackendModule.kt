package io.github.tuguzd.restaurantapp.presentation.di

import androidx.security.crypto.EncryptedSharedPreferences
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.tuguzd.restaurantapp.data.datasource.api.client_work.OrderApi
import io.github.tuguzd.restaurantapp.data.datasource.api.client_work.OrderItemApi
import io.github.tuguzd.restaurantapp.data.datasource.api.meal.MenuApi
import io.github.tuguzd.restaurantapp.data.datasource.api.meal.MenuItemApi
import io.github.tuguzd.restaurantapp.data.datasource.api.organization.ServiceApi
import io.github.tuguzd.restaurantapp.data.datasource.api.organization.ServiceItemApi
import io.github.tuguzd.restaurantapp.data.datasource.api.organization.ServiceItemPointApi
import io.github.tuguzd.restaurantapp.data.datasource.api.role_access_control.UserApi
import io.github.tuguzd.restaurantapp.data.datasource.api.util.AuthApi
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
    @Singleton
    fun provideAuthInterceptorClient(sharedPreferences: EncryptedSharedPreferences): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = sharedPreferences.getString("access_token", null).orEmpty()
                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(converterFactory: Converter.Factory): Retrofit =
        retrofit(backendBaseUrl, converterFactory)

    @Provides
    @Singleton
    @UserRetrofit
    fun provideUserRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}users/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @OrderRetrofit
    fun provideOrderRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}orders/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @OrderItemRetrofit
    fun provideOrderItemRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}order_items/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @MenuRetrofit
    fun provideMenuRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}menus/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @MenuItemRetrofit
    fun provideMenuItemRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}menu_items/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @ServiceRetrofit
    fun provideServiceRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}services/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @ServiceItemRetrofit
    fun provideServiceItemRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}service_items/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    @ServiceItemPointRetrofit
    fun provideServiceItemPointRetrofit(
        converterFactory: Converter.Factory,
        authInterceptorClient: OkHttpClient,
    ): Retrofit =
        retrofit(
            "${backendBaseUrl}service_item_points/",
            converterFactory, authInterceptorClient
        )

    @Provides
    @Singleton
    fun providesAuthAPI(
        @AuthRetrofit retrofit: Retrofit
    ): AuthApi = retrofit.create()

    @Provides
    @Singleton
    fun providesUserAPI(
        @UserRetrofit retrofit: Retrofit
    ): UserApi = retrofit.create()

    @Provides
    @Singleton
    fun providesOrderAPI(
        @OrderRetrofit retrofit: Retrofit
    ): OrderApi = retrofit.create()

    @Provides
    @Singleton
    fun providesOrderItemAPI(
        @OrderItemRetrofit retrofit: Retrofit
    ): OrderItemApi = retrofit.create()

    @Provides
    @Singleton
    fun providesMenuAPI(
        @MenuRetrofit retrofit: Retrofit
    ): MenuApi = retrofit.create()

    @Provides
    @Singleton
    fun providesMenuItemAPI(
        @MenuItemRetrofit retrofit: Retrofit
    ): MenuItemApi = retrofit.create()

    @Provides
    @Singleton
    fun providesServiceAPI(
        @ServiceRetrofit retrofit: Retrofit
    ): ServiceApi = retrofit.create()

    @Provides
    @Singleton
    fun providesServiceItemAPI(
        @ServiceItemRetrofit retrofit: Retrofit
    ): ServiceItemApi = retrofit.create()

    @Provides
    @Singleton
    fun providesServiceItemPointAPI(
        @ServiceItemPointRetrofit retrofit: Retrofit
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
