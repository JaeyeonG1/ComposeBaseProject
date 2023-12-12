package com.tambi.project.flavor

import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.ApplicationVariantDimension
import com.android.build.api.dsl.ProductFlavor
import com.tambi.common.flavor.Flavor
import com.tambi.common.flavor.FlavorDimension
import com.tambi.extensions.androidExtension
import com.tambi.project.ProjectConfig
import com.tambi.project.Versions
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

internal enum class AppFlavor(
    override val flavorName: String,
    override val applicationIdSuffix: String = "",
    override val applicationNamePrefix: String = "",
    override val applicationVersionNameSuffix: String = ""
) : Flavor {

    Develop(
        flavorName = "dev",
        applicationIdSuffix = ".dev",
        applicationNamePrefix = "(dev)",
        applicationVersionNameSuffix = "-dev."
    ),

    Staging(
        flavorName = "staging",
        applicationIdSuffix = ".staging",
        applicationNamePrefix = "(stg)",
        applicationVersionNameSuffix = "-stg."
    ),

    Production(
        flavorName = "prod",
    );

    override fun getVersionCode() = with(Versions) {
        return@with when (this@AppFlavor) {
            Develop -> DevVersionCode
            Staging -> StgVersionCode
            Production -> ProdVersionCode
        }
    }

    override fun getVersionName() = with(Versions) {
        val commonVersionName = "$MajorVersion.$MinorVersion.$PatchVersion"
        val flavorVersionName = when (this@AppFlavor) {
            Develop -> "$applicationVersionNameSuffix$DevelopVersion"
            Staging -> "$applicationVersionNameSuffix$StagingVersion"
            Production -> ""
        }
        return@with commonVersionName + flavorVersionName
    }

    override fun buildFlavor(
        target: Project,
        flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit
    ) {
        target.androidExtension.productFlavors {
            create(flavorName) {
                dimension = FlavorDimension.mode.name
                flavorConfigurationBlock(this, this@AppFlavor)

                setBuildConfigFields(target, this@AppFlavor)
                setManifestPlaceHolders(this@AppFlavor)

                if (this is ApplicationVariantDimension)
                    applicationIdSuffix = this@AppFlavor.applicationIdSuffix

                if (this is ApplicationProductFlavor)
                    setFlavorVersions(this@AppFlavor)
            }
        }
    }

    private fun ProductFlavor.setBuildConfigFields(
        project: Project,
        flavor: AppFlavor
    ) {
        Properties().apply {
            with(File(project.rootDir, "secret.properties")) {
                if (isFile)
                    InputStreamReader(FileInputStream(this), Charsets.UTF_8).use { load(it) }
            }
        }.run {
            setBuildConfigField(this, "BASE_API_URL") {
                when (flavor) {
                    Develop -> "api_url_dev"
                    Staging -> "api_url_stg"
                    Production -> "api_url_prod"
                }
            }
            setBuildConfigField(this, "SDK_KEY") {
                when (flavor) {
                    Develop -> "sdk_key_dev"
                    Staging -> "sdk_key_stg"
                    Production -> "sdk_key_prod"
                }
            }
        }
    }

    private fun ProductFlavor.setBuildConfigField(
        properties: Properties,
        buildConfigKey: String,
        propertyKey: () -> String
    ) {
        buildConfigField(
            "String",
            buildConfigKey,
            properties.getProperty(propertyKey.invoke())
        )
    }

    private fun ProductFlavor.setManifestPlaceHolders(flavor: AppFlavor) {
        manifestPlaceholders["app_name"] = "${flavor.applicationNamePrefix}${ProjectConfig.appName}"
    }

    private fun ApplicationProductFlavor.setFlavorVersions(flavor: AppFlavor) {
        versionCode = flavor.getVersionCode()
        versionName = flavor.getVersionName()
    }
}
