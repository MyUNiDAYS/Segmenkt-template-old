package com.myunidays.segmenkt

import io.ktor.client.*

expect val httpClient: HttpClient

enum class PlatformType {
    ios,
    android
}

expect val platform: PlatformType
