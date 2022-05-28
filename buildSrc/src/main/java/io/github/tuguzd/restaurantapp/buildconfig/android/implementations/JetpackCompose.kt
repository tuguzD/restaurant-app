package io.github.tuguzd.restaurantapp.buildconfig.android.implementations

import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.JetpackCompose
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.composeCoreImplementation() {
    implementation(JetpackCompose.Core.ui)
    implementation(JetpackCompose.Core.activity)
}

fun DependencyHandler.composeIdeImplementation() {
    implementation(JetpackCompose.AndroidStudio.preview)
    debugImplementation(JetpackCompose.AndroidStudio.tooling)
}

fun DependencyHandler.composeThirdPartyImplementation() {
    implementation(JetpackCompose.ThirdParty.coil)
    implementation(JetpackCompose.ThirdParty.scalableDp)
    implementation(JetpackCompose.ThirdParty.SharedElement.dependency)
}

fun DependencyHandler.accompanistFeatureImplementation() {
    implementation(JetpackCompose.Accompanist.pager)
    implementation(JetpackCompose.Accompanist.placeholder)
    implementation(JetpackCompose.Accompanist.refreshSwipe)
}
