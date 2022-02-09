package com.myunidays.segmenkt

import android.app.Application
import android.os.Build
import com.myunidays.segmenkt.models.OperatingSystem
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

actual val httpClient = HttpClient(Android.create()) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

actual val platform = PlatformType.android

actual val operatingSystem = OperatingSystem(
    name = "Android",
    version = "${Build.VERSION.SDK_INT}"
)

actual typealias ApplicationContext = Application
