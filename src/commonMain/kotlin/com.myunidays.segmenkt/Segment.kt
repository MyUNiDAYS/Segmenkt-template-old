package com.myunidays.segmenkt

expect class Configuration {
    val writeKey: String
//    var application: Any?
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
}

expect fun Config(writeKey: String, context: Any? = null): Configuration

expect class Analytics

expect fun Analytics.track(name: String, properties: Map<Any?, *>? = null)

expect fun Analytics.identify(userId: String, traits: Map<Any?, *>? = null)

expect fun Analytics.screen(screenTitle: String, properties: Map<Any?, *>? = null, category: String = "")

expect fun Analytics.group(groupId: String, traits: Map<Any?, *>? = null)

expect fun setupWithConfiguration(configuration: Configuration): Analytics
