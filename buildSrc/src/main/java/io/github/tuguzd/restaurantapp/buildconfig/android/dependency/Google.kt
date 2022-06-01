package io.github.tuguzd.restaurantapp.buildconfig.android.dependency

import io.github.tuguzd.restaurantapp.buildconfig.android.implementation.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object Google {
    object PlayServices {
        const val auth = "com.google.android.gms:play-services-auth:20.2.0"
    }

    fun DependencyHandler.googleImplementation() =
        implementation(PlayServices.auth)
}
