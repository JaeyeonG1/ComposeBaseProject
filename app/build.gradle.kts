plugins {
    alias(libs.plugins.convention.android.application)
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.feature.main)
}
