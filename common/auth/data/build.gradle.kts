plugins {
    id("android-setup")
    id("multiplatform-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.core)
                implementation(projects.common.auth.domain)
            }
        }
    }
}