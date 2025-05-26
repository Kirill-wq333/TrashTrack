plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
}

android {
    namespace = "com.example.trashtrack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.trashtrack"
        minSdk = 28
        targetSdk = 34
        versionCode = 102
        versionName = "1.02"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        exclude("com/j256/ormlite/core/README.txt")
        pickFirst("com/j256/ormlite/dao/BaseDaoImpl.class")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.animation)  // Add this line
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.runtime.livedata)
    debugImplementation(libs.ui.tooling)

    implementation(libs.cloudy)

    implementation(libs.compose.navigation)
    implementation(libs.compose.navigation.hilt)
    implementation(libs.jetbrains.kotlinx.serialization.json)

    implementation(platform(libs.compose.bom.v20230300))
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.ui.graphics)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose.v110)

    implementation(libs.accompanist.permissions)

    implementation(libs.osmdroid.android)

    implementation(libs.androidx.security.crypto)
    // Для хеширования паролей:
    implementation(libs.argon2kt)

}