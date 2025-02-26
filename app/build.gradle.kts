plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) version "2.0.21"  // Asegúrate de usar la misma versión
    id("com.google.devtools.ksp") version "2.0.21-1.0.27"  // Asegúrate de que esta versión esté alineada con la de Kotlin
}

android {
    namespace = "es.etg.pmdm05.fitfusionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "es.etg.pmdm05.fitfusionapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    //ViewBinding
    buildFeatures{
        viewBinding = true
        dataBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.databinding.runtime)
    //BASE DE DATOS
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")




    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //implementation (libs.androidx.preference)
    implementation(libs.androidx.mediarouter) //preferencias

    // FRAGMENTOS
    val fragment_version = "1.8.3"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    //retrofit
    implementation(libs.retrofit)
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")

    //corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")



    implementation ("androidx.cardview:cardview:1.0.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}