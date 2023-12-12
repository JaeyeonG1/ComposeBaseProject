package com.tambi.project.config

import com.tambi.common.config.configureCoroutineAndroid
import com.tambi.common.config.configureHiltAndroid
import com.tambi.common.config.configureKotlinAndroid
import org.gradle.api.Project

internal fun Project.configureAndroidLibrary() {
    pluginManager.apply("com.android.library")

    configureKotlinAndroid()
    configureCoroutineAndroid()
    configureHiltAndroid()
}
