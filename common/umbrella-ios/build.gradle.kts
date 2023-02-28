plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("native.cocoapods")
    //
}

version = "0.0.1"

kotlin {
    cocoapods {
        summary = "ArkDriver iOS SDK"
        homepage = "https://google.com"
        ios.deploymentTarget = "14.0"
        framework {
            transitiveExport = false // Мы не будем подключать зависимости зависимостей
            isStatic = false // Статическая линковка проектов
            baseName = "SharedSDK"
            linkerOpts.add("-lsqlite3")

            export(libs.moko.mvvm.core)
            export(libs.moko.mvvm.flow)

            export(libs.moko.resources)
            export(project(":common:umbrella-core"))
        }

        sourceSets {
            commonMain {
                dependencies {

                }
            }

            iosMain {
                dependencies {

                }
            }
        }
    }
}