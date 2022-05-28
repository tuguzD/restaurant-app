package io.github.tuguzd.restaurantapp.buildconfig.android.implementations

import io.github.tuguzd.restaurantapp.buildconfig.android.dependencies.JetpackCompose
import org.gradle.api.artifacts.dsl.DependencyHandler

// Provide app theming with Material 3
fun DependencyHandler.materialThemeImplementation() {
    implementation(JetpackCompose.Material.You.dependency)
    implementation(JetpackCompose.Material.You.resizableLayout)
}

// Provide Material icons for use without downloading files
fun DependencyHandler.materialIconImplementation() {
    implementation(JetpackCompose.Material.Icons.dependency)
    implementation(JetpackCompose.Material.Icons.extention)
}

// Provide features to build complete Material app
fun DependencyHandler.materialDesignImplementation() {
    materialIconImplementation()
    materialThemeImplementation()
}
