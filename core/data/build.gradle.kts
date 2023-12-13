plugins {
    alias(libs.plugins.convention.android.library)
}

dependencies {
    implementation(libs.bundles.network)
    implementation(libs.bundles.storage)

    implementation(projects.core.domain)
    implementation(projects.core.model)
}
