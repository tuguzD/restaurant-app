import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.AndroidBase.androidBaseImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.Hilt.hiltImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.JetpackCompose.composeVersion
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.KotlinX
import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.*

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
        minSdk = 21
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
    androidBaseImplementation()

    // Jetpack Compose
    composeCoreImplementation()
    materialDesignImplementation()
    navigationImplementation()

    // Additional features for Jetpack Compose
    composeThirdPartyImplementation()
    accompanistFeatureImplementation()

    // Kotlin extensions
    implementation(KotlinX.coroutine)
    implementation(KotlinX.serializationJson)

    // Dependency injection
    hiltImplementation()

    // Domain layer
    // implementation(DomainLayer.dependency) {
    //     isChanging = true
    // }

    // Quality Assurance
    androidTestImplementation(KotlinX.Test.coroutine) {
        exclude(group = KotlinX.group, module = KotlinX.Test.excludedModule)
    }
    loggingImplementation()
    unitTestingImplementation()
    instrumentTestingImplementation()
}

kapt {
    correctErrorTypes = true
}
