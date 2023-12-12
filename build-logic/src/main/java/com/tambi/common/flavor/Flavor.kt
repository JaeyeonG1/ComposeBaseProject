package com.tambi.common.flavor

import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

@Suppress("EnumEntryName")
enum class FlavorDimension {
    mode
}

interface Flavor {
    val flavorName: String
    val applicationIdSuffix: String
    val applicationNamePrefix: String
    val applicationVersionNameSuffix: String

    fun getVersionCode(): Int

    fun getVersionName(): String

    fun buildFlavor(
        target: Project,
        flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit
    )
}
