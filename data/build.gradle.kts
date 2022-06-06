import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.AndroidX
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.DomainLayer
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.Kotlin
import io.github.tuguzd.restaurantapp.buildconfig.android.implementation.retrofitImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementation.roomImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementation.unitTestingImplementation

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    compileSdk = 32
    namespace = "io.github.tuguzd.restaurantapp.domain"

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

dependencies {
    implementation(AndroidX.security)
    implementation(AndroidX.core)

    // Kotlin extensions
    implementation(Kotlin.X.serializationJson)
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha03")

    // Persistence
    roomImplementation()

    // Retrofit
    retrofitImplementation()

    // Domain layer
    implementation(DomainLayer.dependency) { isChanging = true }

    // Quality Assurance
    unitTestingImplementation()
}
