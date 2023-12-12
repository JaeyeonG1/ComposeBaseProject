package com.tambi.common.config

import com.tambi.common.Environment
import com.tambi.extensions.androidExtension
import com.tambi.extensions.coreLibraryDesugar
import com.tambi.extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid() {
    // Plugins
    pluginManager.apply("org.jetbrains.kotlin.android")

    // Android settings
    androidExtension.apply {
        compileSdk = Environment.compileSdk

        defaultConfig {
            minSdk = Environment.minSdk
        }

        compileOptions {
            sourceCompatibility = Environment.JAVA_VERSION
            targetCompatibility = Environment.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }

        // Exclude duplicated META_INF files
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    // Apply Kotlin configure
    configureKotlin()

    dependencies {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        coreLibraryDesugar(versionCatalog.findLibrary("desugarJdkLibs"))
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = Environment.JAVA_VERSION
        targetCompatibility = Environment.JAVA_VERSION
    }

    configureKotlin()
}

/** Configure base Kotlin options */
internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 17
            jvmTarget = Environment.JAVA_VERSION.toString()

            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                // Disable OptIn Annotation Warning
                "-opt-in=kotlin.RequiresOptIn"
            )
        }
    }
}
