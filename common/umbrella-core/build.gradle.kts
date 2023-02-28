plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(projects.common.core)
                implementation(projects.common.auth.data)
//                implementation(projects.common.chat.data)
//                implementation(projects.common.favoriteAddress.data)
//                implementation(projects.common.rideRequest.data)
            }
        }
    }
}