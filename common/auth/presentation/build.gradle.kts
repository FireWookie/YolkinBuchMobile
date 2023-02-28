plugins {
    id("android-setup")
    id("multiplatform-setup")
}


kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.common.core)
                implementation(projects.common.coreUtils)
                implementation(projects.common.auth.domain)
                implementation(projects.common.resources)
            }
        }
    }
}
