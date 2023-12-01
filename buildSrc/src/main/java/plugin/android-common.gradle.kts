package plugin

import AppConfig
import baseDependencies
import composeDependencies
import implementation
import testDependencies


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
    }

    buildTypes {
        getByName("debug") {
        }
        create("stage") {
        }
        getByName("release") {
        }
    }
    compileOptions {
        sourceCompatibility = Version.JAVA_VERSION
        targetCompatibility = Version.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = Version.JAVA_VERSION.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.KotlinCompilerExtension
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kapt {
    correctErrorTypes = true
}


dependencies {
    baseDependencies()
    testDependencies()
    composeDependencies()
}




