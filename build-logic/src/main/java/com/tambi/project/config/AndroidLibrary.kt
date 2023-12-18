package com.tambi.project.config

import com.android.build.gradle.LibraryExtension
import com.tambi.common.config.configureCoroutineAndroid
import com.tambi.common.config.configureHiltAndroid
import com.tambi.common.config.configureKotlinAndroid
import com.tambi.project.ProjectConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidLibrary() {
    pluginManager.apply("com.android.library")

    extensions.configure<LibraryExtension> {
        val uniqueNamespace = project.displayName.substring("project ".length)
            .replace("'", "")
            .replace('-', '_')
            .replace(':', '.')

        namespace = "${ProjectConfig.namespace}${uniqueNamespace}"
    }

    configureKotlinAndroid()
    configureCoroutineAndroid()
    configureHiltAndroid()
}
