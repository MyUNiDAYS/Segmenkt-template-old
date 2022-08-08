
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.android.library") version "7.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    `maven-publish`
    signing
    kotlin("native.cocoapods") version "1.6.21"
}

group = "com.myunidays"
version = "0.0.6"

val frameworkName = "segmenkt"
val ktor_version = "1.6.6"
val coroutines_version = "1.6.0-native-mt"
val serialization_version = "1.3.0"
val napier_version = "2.6.1"
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
    ios {
        binaries.framework {
            baseName = frameworkName
            xcf.add(this)
        }
    }
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
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

        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

kotlin {
    cocoapods {
        ios.deploymentTarget = "10.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        framework { }
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
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 32
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

fun SigningExtension.whenRequired(block: () -> Boolean) {
    setRequired(block)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

publishing {
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")

            credentials {
                username = System.getenv("sonatypeUsernameEnv")
                password = System.getenv("sonatypePasswordEnv")
            }
        }
    }

    publications.all {
        this as MavenPublication

        artifact(javadocJar)

        pom {
            name.set("Segmenkt")
            description.set("Segment Wrapper")
            url.set("https://github.com/Reedyuk/Segmenkt")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("http://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set("Reedyuk")
                    name.set("Andrew Reed")
                    email.set("andrew.reed@myunidays.com")
                }
            }

            scm {
                url.set("https://github.com/Reedyuk/Segmenkt")
                connection.set("scm:git:git://git@github.com:Reedyuk/Segmenkt.git")
                developerConnection.set("scm:git:ssh://git@github.com:Reedyuk/Segmenkt.git")
            }
        }
    }
}

signing {
    whenRequired { gradle.taskGraph.hasTask("publish") }
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}
