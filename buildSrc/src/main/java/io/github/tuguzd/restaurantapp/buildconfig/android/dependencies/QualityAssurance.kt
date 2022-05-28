package io.github.tuguzd.restaurantapp.buildconfig.android.dependencies

import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.androidTestImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.debugImplementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.implementation
import io.github.tuguzd.restaurantapp.buildconfig.android.implementations.testImplementation
import org.gradle.api.artifacts.dsl.DependencyHandler

object QualityAssurance {
    object UnitTest {
        private const val jUnit4 = "junit:junit:4.13.2"
        private const val jUnitAndroidX = "androidx.test.ext:junit:1.1.3"

        fun DependencyHandler.unitTestingImplementation() {
            testImplementation(jUnit4)
            androidTestImplementation(jUnitAndroidX)
        }
    }

    object Log {
        private const val slf4j = "org.slf4j:slf4j-android:1.7.36"
        private const val loggingKotlin = "io.github.microutils:kotlin-logging-jvm:2.1.23"

        fun DependencyHandler.loggingImplementation() {
            implementation(slf4j)
            implementation(loggingKotlin)
        }
    }

    fun DependencyHandler.instrumentTestingImplementation() {
        debugImplementation(JetpackCompose.InstrumentTest.layout)
        androidTestImplementation(JetpackCompose.InstrumentTest.jUnit4)
    }
}
