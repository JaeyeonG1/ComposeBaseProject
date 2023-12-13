package com.tambi.common.config

import com.tambi.extensions.androidExtension
import com.tambi.extensions.androidTestImplementation
import com.tambi.extensions.debugImplementation
import com.tambi.extensions.implementation
import com.tambi.extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

internal fun Project.configureComposeAndroid() {
    androidExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                versionCatalog.findVersion("composeCompiler").get().toString()
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
            }
        }

        dependencies {
            val bom = versionCatalog.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            implementation(versionCatalog.findBundle("compose"))
            androidTestImplementation(versionCatalog.findBundle("compose-test"))
            debugImplementation(versionCatalog.findBundle("compose-debug"))
        }
    }
}

/**
 * Compose Compiler 디버깅 관련 metric 추가.
 *
 * **See Also:** [About Compose Compiler Debugging](https://sungbin.land/jetpack-compose-%EC%BB%B4%ED%8C%8C%EC%9D%BC%EB%9F%AC-%EB%94%94%EB%B2%84%EA%B9%85-cf21ce431387)
 */
private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
