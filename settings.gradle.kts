pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "YolkinBuch"
include(":androidApp")
include(":shared")

include(":common:auth:data")
include(":common:auth:domain")
include(":common:auth:presentation")

include(":common:resources")
include(":common:core")
include(":common:core-utils")
include(":common:umbrella-core")
include(":common:umbrella-ios")