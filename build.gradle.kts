import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.targets

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}

subprojects {
    afterEvaluate {
        kotlinExtension.targets.forEach {
            (it as? KotlinAndroidTarget)?.apply {
                compilations.all {
                    kotlinOptions.jvmTarget = "1.8"
                }
            }
        }
    }
}