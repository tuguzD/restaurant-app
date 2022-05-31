package io.github.tuguzd.restaurantapp.buildconfig.android.implementations

import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.AndroidX
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.JetpackCompose
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.Kotlin
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.QualityAssurance
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.unitTestingImplementation() {
    testImplementation(QualityAssurance.UnitTest.jUnit4)
    androidTestImplementation(AndroidX.UnitTest.jUnit)
}

fun DependencyHandler.loggingImplementation() {
    implementation(Kotlin.logger)
    implementation(AndroidX.Log.logger)
}

fun DependencyHandler.instrumentTestingImplementation() {
    debugImplementation(JetpackCompose.InstrumentTest.layout)
    androidTestImplementation(JetpackCompose.InstrumentTest.jUnit4)
}
