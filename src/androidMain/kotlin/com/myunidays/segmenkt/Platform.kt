package com.myunidays.segmenkt

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*

actual val httpClient = HttpClient(Android.create()) {
    install(JsonFeature)
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
