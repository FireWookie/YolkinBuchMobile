plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("dev.icerock.mobile.multiplatform-resources")
}


multiplatformResources {
    multiplatformResourcesPackage = "com.yolkin.buch"
    iosBaseLocalizationRegion = "ru" // optional, default "en"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
            }
        }
    }
}