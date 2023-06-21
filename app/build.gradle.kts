plugins {
    alias(libs.plugins.android.application)
    kotlin("android") version libs.versions.kotlin
    kotlin("kapt") version libs.versions.kotlin
}

kotlin.jvmToolchain(libs.versions.jdk.get().toInt())

android {
    namespace = "$RELEASE_GROUP.$RELEASE_ARTIFACT"
    testNamespace = "$namespace.test"
    compileSdk = libs.versions.sdk.target.get().toInt()
    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        version = RELEASE_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        applicationId = namespace
    }
    compileOptions {
        targetCompatibility = JavaVersion.toVersion(libs.versions.jdk.get())
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jdk.get())
    }
    kotlinOptions {
        jvmTarget = JavaVersion.toVersion(libs.versions.jdk.get()).toString()
    }
    buildTypes {
        debug {
            enableAndroidTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    ktlint(libs.ktlint, ::configureKtlint)
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.multidex)
}
