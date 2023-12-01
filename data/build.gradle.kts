plugins {
    id("plugin.android-common")
}

android {
    namespace = "kr.co.psk.data"
}

dependencies {
    COMMON
    networkDependencies()
    roomDependencies()
    datastoreDependency()
}