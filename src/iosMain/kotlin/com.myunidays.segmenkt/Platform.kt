package com.myunidays.segmenkt

import com.myunidays.segmenkt.models.App
import com.myunidays.segmenkt.models.OperatingSystem
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import platform.UIKit.UIDevice
import platform.Foundation.NSBundle

actual val httpClient = HttpClient(Ios.create()) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

actual val platform = PlatformType.ios

actual val operatingSystem = OperatingSystem(
    name = UIDevice.currentDevice.systemName(),
    version = UIDevice.currentDevice.systemVersion
)

actual val app = App(
    name = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleName") as? String?,
    namespace = NSBundle.mainBundle.bundleIdentifier,
    version = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String?,
    build = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String?
)
