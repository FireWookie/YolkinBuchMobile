plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.yolkin.buch.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.yolkin.buch.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(projects.common.resources)
    implementation(projects.common.auth.presentation)
    implementation(projects.common.auth.domain)
    implementation(projects.common.umbrellaCore)
    implementation(projects.common.core)
    
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)

    implementation(libs.moko.mvvm.flow)
    implementation(libs.moko.mvvm.flow.compose)
    implementation(libs.moko.mvvm.core)

    implementation(libs.odyssey.compose)
    implementation(libs.odyssey.core)

    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
}