plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.airemodeler"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.airemodeler"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    //noinspection UseTomlInstead
    implementation(libs.androidx.constraintlayout)

    // Text-to-Speech and Coroutine support
    implementation(libs.androidx.lifecycle.runtime.ktx.v280) // latest stable
    implementation(libs.kotlinx.coroutines.android) // adjust to match your project

    // ML Kit Image Labeling
    implementation(libs.image.labeling)

    // ⚠️ OpenCV - you CANNOT mix Maven + .aar at the same time!
    // Choose one: Maven OR local AAR, not both.
    // If using local .aar:
    implementation(files("libs/opencv-android-4.5.3.aar"))
    implementation(libs.object1.detection)
}
