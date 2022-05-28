package io.github.tuguzd.restaurantapp.buildconfig.android.dependencies

import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object AndroidBase {
    private const val core = "androidx.core:core-ktx:1.7.0"
    private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    fun DependencyHandler.androidBaseImplementation() {
        implementation(core)
        implementation(lifecycle)
    }
}
