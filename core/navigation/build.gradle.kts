plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.compose)
}

dependencies {
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.navigation.testing)
}
