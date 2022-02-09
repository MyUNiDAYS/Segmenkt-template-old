package com.myunidays.segmenkt

import com.myunidays.segmenkt.models.OperatingSystem
import io.ktor.client.*

expect val httpClient: HttpClient

expect val platform: PlatformType

expect val operatingSystem: OperatingSystem

enum class PlatformType {
    ios,
    android
}

expect class ApplicationContext
