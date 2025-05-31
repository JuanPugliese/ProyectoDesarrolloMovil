// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id 'com.google.gms.google-services' version '4.4.2' apply false
    id 'androidx.navigation.safeargs.kotlin' version '2.7.7' apply false  // Corregido
}

buildscript {
    // Variables comunes para todos los módulos
    ext {
        nav_version = "2.7.7"  // Usa versión actualizada
        lifecycle_version = "2.6.2"
        room_version = "2.6.1"
    }
}