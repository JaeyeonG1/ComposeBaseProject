plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.hilt.gradlePlugin)
    implementation(libs.ktlint.gradlePlugin)
    implementation(libs.ktlint.compose.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
}
