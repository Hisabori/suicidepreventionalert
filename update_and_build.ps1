# Update gradle-wrapper.properties
Set-Content -Path ./gradle/wrapper/gradle-wrapper.properties -Value "distributionUrl=https://services.gradle.org/distributions/gradle-8.2-bin.zip"

# Update build.gradle.kts
Set-Content -Path ./app/build.gradle.kts -Value @'
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.suicidepreventionalert"
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.suicidepreventionalert"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
'@

# Update settings.gradle.kts
Set-Content -Path ./settings.gradle.kts -Value @'
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyApplication"
include(":app")
'@

# Invalidate Gradle cache
./gradlew cleanBuildCache

# Clean the project
./gradlew clean

# Rebuild the project
./gradlew build
