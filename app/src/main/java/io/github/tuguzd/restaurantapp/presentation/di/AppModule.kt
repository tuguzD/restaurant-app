package io.github.tuguzd.restaurantapp.presentation.di

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.tuguzd.restaurantapp.data.datasource.local.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.access_control.user.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.client_work.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.meal.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.api.organization.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.impl.access_control.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.impl.access_control.user.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.impl.client_work.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.impl.meal.*
import io.github.tuguzd.restaurantapp.data.datasource.remote.impl.organization.*
import io.github.tuguzd.restaurantapp.data.declar.access_control.*
import io.github.tuguzd.restaurantapp.data.declar.access_control.user.*
import io.github.tuguzd.restaurantapp.data.declar.client_work.*
import io.github.tuguzd.restaurantapp.data.declar.meal.*
import io.github.tuguzd.restaurantapp.data.declar.organization.*
import io.github.tuguzd.restaurantapp.data.repository.access_control.*
import io.github.tuguzd.restaurantapp.data.repository.access_control.user.*
import io.github.tuguzd.restaurantapp.data.repository.client_work.*
import io.github.tuguzd.restaurantapp.data.repository.meal.*
import io.github.tuguzd.restaurantapp.data.repository.organization.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json

    private const val serverClientId =
        "721437970114-c1pn1c5bpge8iru30l1td5km894pj5db.apps.googleusercontent.com"

    @Provides
    @Singleton
    fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(serverClientId)
            .requestEmail().build()
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ApplicationContext context: Context,
        options: GoogleSignInOptions,
    ): GoogleSignInClient = GoogleSignIn.getClient(context, options)

    @Provides
    fun provideLastSignedInGoogleAccount(@ApplicationContext context: Context): GoogleSignInAccount? =
        GoogleSignIn.getLastSignedInAccount(context)

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context): EncryptedSharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val fileName = "user-credentials"
        val encryptedSharedPreferences = EncryptedSharedPreferences.create(
            fileName, masterKeyAlias, context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
        return encryptedSharedPreferences as EncryptedSharedPreferences
    }

    @Provides
    fun provideAuthDataSource(authAPI: AuthApi):
        AuthDeclaration = RemoteAuthDataSource(authAPI)

    @Provides
    fun provideAuthRepository(dataSource: AuthDeclaration):
        AuthRepository = AuthRepository(dataSource)

    @Provides
    fun provideUserDataSource(userApi: UserApi):
        UserDeclaration = RemoteUserDataSource(userApi)

    @Provides
    fun provideUserRepository(dataSource: UserDeclaration):
        UserRepository = UserRepository(dataSource)

    @Provides
    fun provideUserTokenRepository(dataSource: UserTokenDeclaration):
        UserTokenRepository = UserTokenRepository(dataSource)

    @Provides
    @Singleton
    fun provideCurrentUserRepository():
        CurrentUserRepository = CurrentUserRepository()

    @Provides
    fun provideOrderDataSource(orderApi: OrderApi):
        OrderDeclaration = RemoteOrderDataSource(orderApi)

    @Provides
    fun provideOrderRepository(dataSource: OrderDeclaration):
        OrderRepository = OrderRepository(dataSource)

    @Provides
    fun provideOrderItemDataSource(orderItemApi: OrderItemApi):
        OrderItemDeclaration = RemoteOrderItemDataSource(orderItemApi)

    @Provides
    fun provideOrderItemRepository(dataSource: OrderItemDeclaration):
        OrderItemRepository = OrderItemRepository(dataSource)

    @Provides
    fun provideMenuDataSource(menuApi: MenuApi):
        MenuDeclaration = RemoteMenuDataSource(menuApi)

    @Provides
    fun provideMenuRepository(dataSource: MenuDeclaration):
        MenuRepository = MenuRepository(dataSource)

    @Provides
    fun provideMenuItemDataSource(menuItemApi: MenuItemApi):
        MenuItemDeclaration = RemoteMenuItemDataSource(menuItemApi)

    @Provides
    fun provideMenuItemRepository(dataSource: MenuItemDeclaration):
        MenuItemRepository = MenuItemRepository(dataSource)

    @Provides
    fun provideServiceDataSource(serviceApi: ServiceApi):
        ServiceDeclaration = RemoteServiceDataSource(serviceApi)

    @Provides
    fun provideServiceRepository(dataSource: ServiceDeclaration):
        ServiceRepository = ServiceRepository(dataSource)

    @Provides
    fun provideServiceItemDataSource(serviceItemApi: ServiceItemApi):
        ServiceItemDeclaration = RemoteServiceItemDataSource(serviceItemApi)

    @Provides
    fun provideServiceItemRepository(dataSource: ServiceItemDeclaration):
        ServiceItemRepository = ServiceItemRepository(dataSource)

    @Provides
    fun provideServiceItemPointDataSource(serviceItemPointApi: ServiceItemPointApi):
        ServiceItemPointDeclaration = RemoteServiceItemPointDataSource(serviceItemPointApi)

    @Provides
    fun provideServiceItemPointRepository(dataSource: ServiceItemPointDeclaration):
        ServiceItemPointRepository = ServiceItemPointRepository(dataSource)

    @Provides
    fun provide(sharedPreferences: EncryptedSharedPreferences):
        UserTokenDeclaration = LocalUserTokenDataSource(sharedPreferences)
}
