package com.myunidays.segmenkt

//import com.myunidays.segmenkt.models.OperatingSystem

expect val platform: PlatformType

enum class PlatformType {
    ios,
    android
}
/*
expect val operatingSystem: OperatingSystem

expect class ApplicationContext*/
