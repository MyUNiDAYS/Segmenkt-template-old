package com.myunidays.segmenkt

data class Configuration(
    val writeKey: String,
    val application: Any?
/*//    val storageProvider: StorageProvider,
    var collectDeviceId: Boolean
    var trackApplicationLifecycleEvents: Boolean
    var useLifecycleObserver: Boolean
    var trackDeepLinks: Boolean
    var flushAt: Int
    var flushInterval: Int
//    val defaultSettings: Settings = Settings(),
    var autoAddSegmentDestination: Boolean
    var apiHost: String
    var cdnHost: String

    fun isValid(): Boolean*/
) {
    constructor(writeKey: WriteKey, context: Any? = null) : this(writeKey = writeKey.keyForPlatform(), application = context)
}

object Segment

expect class Analytics {

    companion object {
        fun setupWithConfiguration(configuration: Configuration): Analytics
        fun shared(context: Any? = null): Analytics
    }

    fun track(name: String, properties: Map<Any?, *>? = null)
    fun identify(userId: String, traits: Map<Any?, *>? = null)
    fun screen(screenTitle: String, properties: Map<Any?, *>? = null)
    fun group(groupId: String, traits: Map<Any?, *>? = null)
}
