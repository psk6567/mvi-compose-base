plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("AndroidCoreLibraryPlugin") {
            id = "android.core.library.plugin"
            implementationClass = "commons.AndroidCoreLibraryPlugin"
        }
    }
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}


/*buildscript {
    repositories {
        flatDir { dirs = setOf(file("plugin")) }
    }
    dependencies {
    }
}*/

dependencies {
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.47")
    implementation("com.google.gms:google-services:4.3.15")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.9.1")
}