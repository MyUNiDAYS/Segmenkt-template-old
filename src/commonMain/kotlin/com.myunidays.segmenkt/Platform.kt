package com.myunidays.segmenkt

import com.myunidays.segmenkt.models.App
import com.myunidays.segmenkt.models.OperatingSystem
import io.ktor.client.*

expect val httpClient: HttpClient

expect val platform: PlatformType

expect val operatingSystem: OperatingSystem

expect val app: App

enum class PlatformType {
    ios,
    android
}
