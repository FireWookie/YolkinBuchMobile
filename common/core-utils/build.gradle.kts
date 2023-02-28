plugins {
    id("android-setup")
    id("multiplatform-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kotlinx.coroutines.core)

                api(libs.koin.core)

                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)
            }
        }

        androidMain {
            dependencies {
                api("androidx.compose.runtime:runtime:${rootProject.extra["compose_version"] as String}")
            }
        }
    }
}