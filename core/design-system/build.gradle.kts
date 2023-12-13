plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.compose)
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.core.splashscreen)
}
