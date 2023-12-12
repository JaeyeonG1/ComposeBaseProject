package com.tambi.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.getByType

/**
 * Get android extension from project and apply [LibraryExtension] or [ApplicationExtension].
 *
 * If cannot get both, throw exception.
 */
val Project.androidExtension: CommonExtension<*, *, *, *, *>
    get() = runCatching { libraryExtension }
        .recoverCatching { applicationExtension }
        .onFailure { println("Could not find Library or Application extension from this project") }
        .getOrThrow()

val Project.applicationExtension: CommonExtension<*, *, *, *, *>
    get() = extensions.getByType<ApplicationExtension>()

val Project.libraryExtension: CommonExtension<*, *, *, *, *>
    get() = extensions.getByType<LibraryExtension>()

/**
 * Get project default [VersionCatalog] named "libs" from [Project].
 */
val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")


/**
 * Get project default [VersionCatalog] named "libs from [ExtensionContainer]".
 */
val ExtensionContainer.versionCatalog: VersionCatalog
    get() = getByType<VersionCatalogsExtension>().named("libs")
