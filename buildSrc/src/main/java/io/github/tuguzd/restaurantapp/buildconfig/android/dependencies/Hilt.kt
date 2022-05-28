package io.github.tuguzd.restaurantapp.buildconfig.android.dependencies

import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.implementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.kapt
import org.gradle.api.artifacts.dsl.DependencyHandler

object Hilt {
    private const val hiltVersion = "2.40.1"

    private const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    private const val dependency = "com.google.dagger:hilt-android:$hiltVersion"

    fun DependencyHandler.hiltImplementation() {
        kapt(compiler)
        implementation(dependency)
    }

    const val plugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
}
