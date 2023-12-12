package com.tambi.common.config

import com.tambi.extensions.implementation
import com.tambi.extensions.kapt
import com.tambi.extensions.kaptAndroidTest
import com.tambi.extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltAndroid() {
    with(pluginManager) {
        apply("dagger.hilt.android.plugin")
        apply("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        implementation(versionCatalog.findLibrary("hilt.android"))
        kapt(versionCatalog.findLibrary("hilt.android.compiler"))
        kaptAndroidTest(versionCatalog.findLibrary("hilt.android.compiler"))
    }

    // Allow hilt annotation processors to be isolating only when necessary
    configure<dagger.hilt.android.plugin.HiltExtension> {
        enableAggregatingTask = true
    }

    // Allow references to generated code
    configure<org.jetbrains.kotlin.gradle.plugin.KaptExtension> {
        correctErrorTypes = true
    }
}

internal fun Project.configureHiltKotlin() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        implementation(versionCatalog.findLibrary("hilt.core"))
        kapt(versionCatalog.findLibrary("hilt.compiler"))
    }

    // Allow references to generated code
    configure<org.jetbrains.kotlin.gradle.plugin.KaptExtension> {
        correctErrorTypes = true
    }
}
