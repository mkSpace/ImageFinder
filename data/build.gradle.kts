plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devtoolsKsp)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.funin.imagefinder.data"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.funin.imagefinder.data"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("Boolean", "IS_DEBUG", "true")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("Boolean", "IS_DEBUG", "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Serialization
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    // Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // SQLCipher
    implementation(libs.sql.cipher)

    // EncryptedSharedPreferences
    implementation(libs.androidx.security.crypto)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}