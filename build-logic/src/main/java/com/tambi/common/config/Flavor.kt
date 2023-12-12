package com.tambi.common.config

import com.android.build.api.dsl.ProductFlavor
import com.tambi.common.flavor.Flavor
import com.tambi.common.flavor.FlavorDimension
import com.tambi.extensions.androidExtension
import org.gradle.api.Project

internal fun Project.configureFlavors(
    flavors: List<Flavor>,
    flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit = {}
) {
    androidExtension.apply {
        flavorDimensions += FlavorDimension.mode.name

        flavors.forEach {
            it.buildFlavor(this@configureFlavors, flavorConfigurationBlock)
        }
    }
}
