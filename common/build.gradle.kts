
plugins{
    id("plugin.android-common")
}

android {
    namespace = "kr.co.psk.common"
    defaultConfig {
        buildConfigField("String", "TEST_WEB_URL", "\"https://google.com\"")
        buildConfigField("String", "SAMPLE_TABLE_NAME", "\"SampleTable\"")
    }
    buildTypes {
        debug{
            buildConfigField("String", "SERVER_URL", "\"https://jsonplaceholder.typicode.com\"")
        }
        getByName("stage") {
            buildConfigField("String", "SERVER_URL", "\"https://jsonplaceholder.typicode.com/\"")
        }
        release {
            buildConfigField("String", "SERVER_URL", "\"https://jsonplaceholder.typicode.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = Version.JAVA_VERSION
        targetCompatibility = Version.JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = Version.JAVA_VERSION.toString()
    }
}

dependencies {
}