plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id 'com.google.gms.google-services'
    id 'androidx.navigation.safeargs.kotlin'  // Plugin correcto para Kotlin
    id 'kotlin-parcelize'
}

android {
    namespace 'com.project.fisiocare'
    compileSdk 34  // Actualizado a 34

    defaultConfig {
        applicationId "com.project.fisiocare"
        minSdk 24
        targetSdk 34  // Actualizado a 34
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
                targetCompatibility JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = '11'
    }

    buildFeatures {
        viewBinding true
    }
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.14.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation libs.firebase.database.ktx
            implementation libs.firebase.firestore.ktx

            // AndroidX Core
            implementation libs.androidx.core.ktx
            implementation libs.androidx.appcompat
            implementation libs.androidx.activity
            implementation libs.androidx.constraintlayout
            implementation libs.material

            // Navigation Component (usando versión definida en el proyecto)
            implementation "androidx.navigation:navigation-fragment-ktx:$rootProject.ext.nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$rootProject.ext.nav_version"

    // Lifecycle
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.ext.lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.ext.lifecycle_version"

    // Testing
    testImplementation libs.junit
            androidTestImplementation libs.androidx.junit
            androidTestImplementation libs.androidx.espresso.core
}