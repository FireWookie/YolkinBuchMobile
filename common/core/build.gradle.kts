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
                api(libs.kotlinx.serialization.core)

                api(libs.ktor.core)

                implementation(projects.common.resources)

                api(libs.ktor.json)
                api(libs.ktor.serialization)
                api(libs.ktor.negotiation)
                api(libs.ktor.kotlin.json)
                api(libs.ktor.logging)

                api(libs.koin.core)
                api(libs.settings)

                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)
            }
        }

        androidMain {
            dependencies {
                api(libs.ktor.android)
            }
        }

        iosMain {
            dependencies {
                api(libs.ktor.ios)
            }
        }
    }
}