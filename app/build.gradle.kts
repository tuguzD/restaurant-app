import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.AndroidX.androidXImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.DomainLayer
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.Google.googleImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.Hilt.hiltImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.JetpackCompose.composeVersion
import io.github.tuguzd.restaurantapp.buildconfig.android.dependency.Kotlin
import io.github.tuguzd.restaurantapp.buildconfig.android.implementation.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32
    namespace = "io.github.tuguzd.restaurantapp"

    defaultConfig {
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
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
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

dependencies {
    // Must-have Android dependencies
    androidXImplementation()

    // Jetpack Compose
    composeCoreImplementation()
    materialDesignImplementation()
    navigationImplementation()

    // Additional features for Jetpack Compose
    composeThirdPartyImplementation()
    accompanistFeatureImplementation()

    // Kotlin extensions
    implementation(Kotlin.X.coroutine)
    implementation(Kotlin.X.serializationJson)

    // Dependency injection
    hiltImplementation()

    // Google
    googleImplementation()

    // Domain layer
    implementation(DomainLayer.dependency) { isChanging = true }
    // Data layer
    implementation(project(":data"))

    // Quality Assurance
    androidTestImplementation(Kotlin.X.Test.coroutine) {
        exclude(group = Kotlin.X.group, module = Kotlin.X.Test.excludedModule)
    }
    loggingImplementation()
    unitTestingImplementation()
    instrumentTestingImplementation()
}

kapt {
    correctErrorTypes = true
}
