
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.android.library") version "7.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    `maven-publish`
    kotlin("native.cocoapods") version "1.6.0"
}

group = "com.myunidays"
version = "0.0.1"

val frameworkName = "segmenkt"
val ktor_version = "1.6.6"
val coroutines_version = "1.5.2-native-mt"
val serialization_version = "1.3.0"
val napier_version = "2.3.0"
val koin_version = "3.1.5"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }

    val xcf = XCFramework()
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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.github.aakira:napier:$napier_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.segment.analytics.android:analytics:4.10.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {}
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosTest by getting
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
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
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

ktlint {
    version.set("0.43.0")
}
