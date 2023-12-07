import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Created by Puskal Khadka on 3/14/2023.
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.composeDependencies() {
    implementation(platform(Libraries.Compose.composeBom))
    implementation(Libraries.Compose.composeActivity)
    implementation(Libraries.Compose.composeUi)
    debugImplementation(Libraries.Compose.composeUiTooling)
    implementation(Libraries.Compose.composeUiTooling)
    implementation(Libraries.Compose.composeUiToolingPreview)
    implementation(Libraries.Compose.composeUiUtil)
    implementation(Libraries.Compose.composeFoundation)
    implementation(Libraries.Compose.composeRuntime)
    implementation(Libraries.Compose.composeMaterial3)

    //navgation
    implementation(Libraries.Naviagtion.navigationCompose)

    //hilt navigation
    implementation(Libraries.Hilt.hiltNavigationCompse)

    //accompanist
    accompanistDependencies()

}

fun DependencyHandler.baseDependencies() {
    implementation(Libraries.AndroidX.appCompat)
    implementation(Libraries.AndroidX.coreKtx)
    implementation(Libraries.AndroidX.lifecycleRunTimeKtx)
    implementation(Libraries.AndroidX.coroutineCore)
    implementation(Libraries.AndroidX.coroutineAndroid)
    implementation(Libraries.AndroidX.collectionImmutable)
    implementation(Libraries.Google.gson)
    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.hiltCompiler)
    implementation(platform(Libraries.FireBase.fireBaseBom))
    implementation(Libraries.FireBase.fireBaseAnalytics)
}

fun DependencyHandler.accompanistDependencies() {
    implementation(Libraries.Accompanist.swiperefresh)
    implementation(Libraries.Accompanist.webView)
    implementation(Libraries.Accompanist.indicators)
    implementation(Libraries.Accompanist.systemuicontroller)
    implementation(Libraries.Accompanist.navigationAnimation)
    implementation(Libraries.Accompanist.permission)
}

fun DependencyHandler.networkDependencies() {
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.retrofitGson)
    implementation(Libraries.Network.retrofitLogging)
    implementation(Libraries.Network.retrofitScalars)
}


fun DependencyHandler.roomDependencies() {
    implementation(Libraries.Room.room)
    implementation(Libraries.Room.roomRX)
    kapt(Libraries.Room.roomCompiler)
}



fun DependencyHandler.testDependencies() {
    androidTestImplementation(Libraries.Test.testCoreKtx)
    androidTestImplementation(Libraries.Test.espressorCore)
    androidTestImplementation(Libraries.Test.runner)
    androidTestImplementation(Libraries.Test.junitExtKtx)
    androidTestImplementation(Libraries.Test.truthExt)
}


fun DependencyHandler.datastoreDependency() {
    implementation(Libraries.DataStore.datastoreCore)
    implementation(Libraries.DataStore.datastore)
    implementation(Libraries.DataStore.datastorePreferences)
    implementation(Libraries.DataStore.datastorePreferencesCore)

}

fun DependencyHandler.fireBaseDependencies() {
    implementation(platform(Libraries.FireBase.fireBaseBom))
    implementation(Libraries.FireBase.fireBaseAnalytics)
}

fun DependencyHandler.orbitDependencies() {
    implementation(Libraries.Orbit.OrbitCore)
    implementation(Libraries.Orbit.OrbitViewModel)
    implementation(Libraries.Orbit.OrbitCompose)
    testImplementation(Libraries.Orbit.OrbitTest)
}

fun DependencyHandler.moduleDependencies() {
    COMMON
    DATA
    DOMAIN
}

val DependencyHandler.DOMAIN
    get() = implementation(project(mapOf("path" to ":domain")))

val DependencyHandler.DATA
    get() = implementation(project(mapOf("path" to ":data")))

val DependencyHandler.COMMON
    get() = implementation(project(mapOf("path" to ":common")))

