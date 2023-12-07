import org.gradle.api.JavaVersion

object Version {
    val JAVA_VERSION = JavaVersion.VERSION_17
    const val CoreKtx = "1.8.10"
    const val ComposeBom = "2023.01.00"
    const val AppCompat = "1.6.1"
    const val Retrofit2 = "2.9.0"
    const val Room = "2.6.0-alpha02"
    const val Coroutine = "1.3.9"
    const val FireBase = "32.1.0"
    const val Coil = "2.4.0"
    const val WebView = "0.30.1"
    const val LifecycleRunTimeKtx = "2.5.1"
    const val NavigationCompose = "2.5.3"
    const val HiltNavigationCompose = "1.0.0"
    const val HiltAndroidVersion = "2.47"
    const val Accompanist = "0.28.0"
    const val SplashScreenApi = "1.1.0-alpha01"
    const val GsonVersion = "2.10.1"
    const val CollectionImmutable = "0.3.6"
    const val DataStore = "1.0.0"
    const val Orbit = "6.1.0"

    //Test
    const val AndroidXTestVersion = "1.5.0"
    const val EspressoCore = "3.5.1"
    const val TestRunnerVersion = "1.5.2"
    const val JunitExtKtx = "1.1.5"
    const val TruthExt = "1.5.0"

}


object Libraries {

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Version.CoreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.AppCompat}"
        const val lifecycleRunTimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LifecycleRunTimeKtx}"
        //const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$LifecycleRunTimeKtx"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutine}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutine}"
        const val collectionImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Version.CollectionImmutable}"
        //const val splashScreen = "androidx.core:core-splashscreen:1.0.1"
    }

    object Compose {
        const val composeBom = "androidx.compose:compose-bom:${Version.ComposeBom}"
        const val composeCompiler = "androidx.compose.compiler:compiler"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeRuntime = "androidx.compose.runtime:runtime"
        const val composeUiUtil = "androidx.compose.ui:ui-util"
        const val composeActivity = "androidx.activity:activity-compose"
        const val composeMaterial3 = "androidx.compose.material3:material3"
    }

    object Google {
        const val gson = "com.google.code.gson:gson:${Version.GsonVersion}"
    }

    object Accompanist {
        const val swiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Version.Accompanist}"
        const val indicators =
            "com.google.accompanist:accompanist-pager-indicators:${Version.Accompanist}"
        const val systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.Accompanist}"
        const val webView = "com.google.accompanist:accompanist-webview:${Version.Accompanist}"
        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:${Version.Accompanist}"
        const val permission =
            "com.google.accompanist:accompanist-permissions:${Version.Accompanist}"
    }

    object Network{
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit2}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Version.Retrofit2}"
        const val retrofitLogging = "com.squareup.okhttp3:logging-interceptor:4.11.0"
        const val retrofitScalars = "com.squareup.retrofit2:converter-scalars:${Version.Retrofit2}"
    }

    object Room {
        const val room = "androidx.room:room-ktx:${Version.Room}"
        const val roomRX = "androidx.room:room-rxjava2:${Version.Room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.Room}"
    }

    object Naviagtion {
        const val navigationCompose = "androidx.navigation:navigation-compose:${Version.NavigationCompose}"
    }

    object DataStore {
        const val datastore = "androidx.datastore:datastore:${Version.DataStore}"
        const val datastoreCore = "androidx.datastore:datastore-core:${Version.DataStore}"
        const val datastorePreferences = "androidx.datastore:datastore-preferences:${Version.DataStore}"
        const val datastorePreferencesCore = "androidx.datastore:datastore-preferences-core:${Version.DataStore}"
    }

    object Test {
        const val testCoreKtx = "androidx.test:core-ktx:${Version.AndroidXTestVersion}"
        const val espressorCore = "androidx.test.espresso:espresso-core:${Version.EspressoCore}"
        const val junitExtKtx = "androidx.test.ext:junit-ktx:${Version.JunitExtKtx}"
        const val truthExt = "androidx.test.ext:truth:${Version.TruthExt}"
        const val runner = "androidx.test:runner:${Version.TestRunnerVersion}"
    }


    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.HiltAndroidVersion}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.HiltAndroidVersion}"
        //hilt compose
        const val hiltNavigationCompse = "androidx.hilt:hilt-navigation-compose:${Version.HiltNavigationCompose}"
    }

    object FireBase {
        const val fireBaseBom = "com.google.firebase:firebase-bom:${Version.FireBase}"
        const val fireBaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object Orbit {
        const val OrbitCore = "org.orbit-mvi:orbit-core:${Version.Orbit}"
        const val OrbitViewModel = "org.orbit-mvi:orbit-viewmodel:${Version.Orbit}"
        const val OrbitCompose = "org.orbit-mvi:orbit-compose:${Version.Orbit}"
        const val OrbitTest = "org.orbit-mvi:orbit-test:${Version.Orbit}"
    }
}


