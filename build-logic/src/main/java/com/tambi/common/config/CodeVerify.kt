package com.tambi.common.config

import com.tambi.extensions.versionCatalog
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCodeVerify() {
    with(pluginManager) {
        apply("org.jlleitschuh.gradle.ktlint")
        apply("io.gitlab.arturbosch.detekt")
    }

    dependencies {
        add("ktlint", versionCatalog.findLibrary("ktlint-compose-gradlePlugin").get())
    }

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
    }
}
