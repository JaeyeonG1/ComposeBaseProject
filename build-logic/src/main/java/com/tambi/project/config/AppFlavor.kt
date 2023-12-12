package com.tambi.project.config

import com.tambi.common.config.configureFlavors
import com.tambi.project.flavor.AppFlavor
import org.gradle.api.Project

internal fun Project.configureAppFlavors() {
    configureFlavors(flavors = AppFlavor.values().asList())
}
