package io.github.tuguzd.restaurantapp.buildconfig.android.implementations

import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.JetpackCompose
import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.QualityAssurance
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.unitTestingImplementation() {
    testImplementation(QualityAssurance.UnitTest.jUnit4)
    androidTestImplementation(QualityAssurance.UnitTest.jUnitAndroidX)
}

fun DependencyHandler.loggingImplementation() {
    implementation(QualityAssurance.Log.slf4j)
    implementation(QualityAssurance.Log.loggingKotlin)
}

fun DependencyHandler.instrumentTestingImplementation() {
    debugImplementation(JetpackCompose.InstrumentTest.layout)
    androidTestImplementation(JetpackCompose.InstrumentTest.jUnit4)
}
