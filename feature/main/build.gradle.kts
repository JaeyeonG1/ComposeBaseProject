plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.compose)
}


dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.navigation)
    implementation(projects.core.domain)
    implementation(projects.core.model)
}
