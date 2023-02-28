import ApkConfig.COMPILE_SDK_VERSION
import ApkConfig.MIN_SDK_VERSION
import ApkConfig.TARGET_SDK_VERSION


plugins {
    id("com.android.library")
}

android {
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION

        consumerProguardFiles("consumer-rules.pro")
    }
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDir("src/androidMain/resources")
            res.srcDir(File(
                buildDir, "generated/moko/androidMain/res"
            ))
        }
    }
    lint {
        quiet = true
        checkReleaseBuilds = false
        abortOnError = false
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        maybeCreate("debug").apply {
            buildConfigField("boolean", "INTERNAL", "true")
//            multiDexEnabled = true
            isMinifyEnabled = false
        }

        maybeCreate("release").apply {
            buildConfigField("boolean", "INTERNAL", "false")
            isMinifyEnabled = true
            consumerProguardFile("proguard-rules.pro")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.1"
    }
}