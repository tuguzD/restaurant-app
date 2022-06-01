package io.github.tuguzd.restaurantapp.buildconfig.android.dependency

object Kotlin {
    object X {
        private const val version = "1.6.2"
        const val group = "org.jetbrains.kotlinx"

        const val coroutine = "$group:kotlinx-coroutines-android:$version"
        const val playServices = "$group:kotlinx-coroutines-play-services:$version"

        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"

        object Test {
            const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
            const val excludedModule = "kotlinx-coroutines-debug"
        }
    }

    const val logger = "io.github.microutils:kotlin-logging-jvm:2.1.23"
}
