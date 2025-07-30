plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.example.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.data)
    implementation(projects.core.database)
}