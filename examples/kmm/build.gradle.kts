import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version "1.6.0"
    id("com.android.library") version "7.1.0"
    kotlin("native.cocoapods") version "1.6.0"
}

group = "com.myunidays"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    mavenLocal()
}

val frameworkName = "SegmentExample"

kotlin {
    val xcf = XCFramework(frameworkName)
    iosSimulatorArm64 {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    iosArm64("ios") {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    android {
        publishAllLibraryVariants()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.myunidays:segmenkt:0.0.1")
            }
        }
        val iosMain by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val androidMain by getting
    }
}

kotlin {
    cocoapods {
        ios.deploymentTarget = "10.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        framework {  }
        pod("Analytics") {
            version = "~> 4.1.6"
            moduleName = "Segment"
            source = git("https://github.com/Reedyuk/analytics-ios.git") {
                branch = "master"
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}