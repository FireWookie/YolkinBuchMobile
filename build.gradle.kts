buildscript {
    val compose_version by extra("1.3.0")
    val compose_ui_version by extra("1.3.0")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.bundles.plaginz)
        classpath("dev.icerock.moko:resources-generator:0.20.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
            url = uri("https://mmr.jfrog.io/artifactory/maps-sdk-android/")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
