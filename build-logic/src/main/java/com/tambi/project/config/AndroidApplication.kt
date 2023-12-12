package com.tambi.project.config

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.tambi.common.config.configureHiltAndroid
import com.tambi.common.config.configureKotlinAndroid
import com.tambi.project.ProjectConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureAndroidApplication() {
    pluginManager.apply("com.android.application")

    extensions.configure<BaseAppModuleExtension> {
        configureKotlinAndroid()
        configureHiltAndroid()

        namespace = ProjectConfig.namespace

        buildFeatures.buildConfig = true

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
                // Disable abi based multi-apk config to reduce build time
                splits.abi.isEnable = false
                // Disable PNG Crunching to reduce build time
                androidResources.noCompress.add("")
            }

            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                /*
                // Alternative to proguard-android.txt
                // https://developer.android.com/build/shrink-code
                postprocessing {
                    isRemoveUnusedCode = true
                    isRemoveUnusedResources = true
                    isOptimizeCode = true
                    isObfuscate = true
                }
                */
            }

            // Remove unused variant to reduce build time - prodDebug, devRelease
            extensions.getByType(AndroidComponentsExtension::class.java).beforeVariants { builder ->
                val buildName = builder.buildType
                val flavorName = builder.flavorName

                val isIgnoreTargetVariant =
                    (flavorName == "prod" && buildName == "debug") ||
                            (flavorName == "dev" && buildName == "release")

                builder.enable = !isIgnoreTargetVariant
            }

            // Set output apk file name to each variant
            applicationVariants.all {
                val variant = this
                outputs.all {
                    (this as BaseVariantOutputImpl).outputFileName = "${variant.versionName}.apk"
                }
            }
        }
    }
}
