package com.myunidays.segmenkt

import com.myunidays.segmenkt.models.OperatingSystem
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import platform.UIKit.UIDevice

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

actual typealias ApplicationContext = Any
