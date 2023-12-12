package com.tambi.common.config

import com.tambi.extensions.implementation
import com.tambi.extensions.testImplementation
import com.tambi.extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineAndroid() {
    configureCoroutineKotlin()
    dependencies {
        implementation(versionCatalog.findLibrary("kotlinx.coroutines.android"))
    }
}

internal fun Project.configureCoroutineKotlin() {
    dependencies {
        implementation(versionCatalog.findLibrary("kotlinx.coroutines.core"))
        testImplementation(versionCatalog.findLibrary("kotlinx.coroutines.test"))
    }
}
