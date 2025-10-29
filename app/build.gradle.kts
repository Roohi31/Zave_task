plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android) version "1.9.24"
//    alias(libs.plugins.google.gms.google.services)
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
}

android {
    namespace = "me.sabapro.zave_task"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.sabapro.zave_task"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    ///
//    implementation(libs.androidx.runtime.livedata)
//    implementation(libs.androidx.material3.android)
    ///
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Json Converter
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Image Loading
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    //remote config
    implementation("com.google.firebase:firebase-config:23.0.1")
//    //google place
//    implementation("com.google.android.libraries.places:places:5.0.0")
    implementation("com.google.android.libraries.places:places:3.4.0")
    /// location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
//    annotationProcessor("androidx.room:room-compiler:2.6.1")

    implementation("androidx.room:room-ktx:2.6.1")

    // Kotlin Symbol Processing (KSP) instead of kapt (recommended)
    ksp("androidx.room:room-compiler:2.6.1")

}

apply { plugin("com.google.gms.google-services")}