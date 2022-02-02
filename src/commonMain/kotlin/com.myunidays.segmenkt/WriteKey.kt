package com.myunidays.segmenkt

data class WriteKey(
    val android: String?,
    val ios: String?
) {

    fun keyForPlatform(): String? = when (platform) {
        PlatformType.ios -> ios
        PlatformType.android -> android
    }

}
