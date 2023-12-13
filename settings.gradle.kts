pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeProject"

include(":app")

include(
    ":core:design-system",
    ":core:navigation",
    ":core:domain",
    ":core:data",
    ":core:model",
)

include(":feature:main")
