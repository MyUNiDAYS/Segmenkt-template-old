plugins {
    kotlin("multiplatform") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
    id("com.android.library") version "7.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

group = "com.myunidays"
version = "0.0.1"

val name = "segmenkt"
val ktor_version = "1.6.6"
val coroutines_version = "1.6.0-native-mt"
val serialization_version = "1.3.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    iosSimulatorArm64 {
        binaries.framework(name)
    }
    iosArm64("ios") {
        binaries.framework(name)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization_version")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("io.ktor:ktor-client-json:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor_version")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktor_version")
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
        val iosTest by getting
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    tasks {
        val capitalizedName = name.capitalize()
        register("copyIosFrameworkIntoUniversalFramework", Copy::class) {
            from(file("$buildDir/bin/ios/${name}ReleaseFramework/$name.framework"))
            into(file("$buildDir/universal/$name.xcframework/ios-arm64/$name.framework"))
        }
        register("copySimulatorFrameworkIntoUniversalFramework", Copy::class) {
            from(file("$buildDir/bin/iosSimulatorArm64/${name}ReleaseFramework/$name.framework"))
            into(file("$buildDir/universal/$name.xcframework/ios-arm64_x86_64-simulator/$name.framework"))
        }
        register("copyPlistIntoUniversalFramework", Copy::class) {
            from(file("${project.projectDir}/Info.plist"))
            into(file("$buildDir/universal/$name.xcframework"))
        }
        register("copyFrameworksIntoUniversalFramework") {
            mustRunAfter("link${capitalizedName}Ios", "link${capitalizedName}IosSimulatorArm64")
            mustRunAfter("link${capitalizedName}Ios")
            dependsOn("copyIosFrameworkIntoUniversalFramework", "copySimulatorFrameworkIntoUniversalFramework", "copyPlistIntoUniversalFramework")
        }
        register("makeUniversalFramework") {
            dependsOn("link${capitalizedName}Ios")
            dependsOn("link${capitalizedName}IosSimulatorArm64")
            dependsOn("copyFrameworksIntoUniversalFramework")
        }
    }
}

ktlint {
    version.set("0.43.0")
}
