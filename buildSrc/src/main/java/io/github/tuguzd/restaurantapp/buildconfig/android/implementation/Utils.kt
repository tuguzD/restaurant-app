package io.github.tuguzd.restaurantapp.buildconfig.android.implementation

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.kapt(dependencyNotation: Any) =
    add("kapt", dependencyNotation)

internal fun DependencyHandler.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any) =
    add("debugImplementation", dependencyNotation)

internal fun DependencyHandler.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)

internal fun DependencyHandler.runtimeOnly(dependencyNotation: Any) =
    add("runtimeOnly", dependencyNotation)

internal fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any) =
    add("testRuntimeOnly", dependencyNotation)
