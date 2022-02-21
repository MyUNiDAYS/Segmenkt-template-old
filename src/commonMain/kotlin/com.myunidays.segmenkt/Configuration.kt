package com.myunidays.segmenkt

/**
 * Configuration that analytics can use
 * @property writeKey the Segment writeKey
 * @property application defaults to `null`
 * @property storageProvider Provider for storage class, defaults to `ConcreteStorageProvider`
 * @property collectDeviceId collect deviceId, defaults to `false`
 * @property trackApplicationLifecycleEvents automatically send track for Lifecycle events (eg: Application Opened, Application Backgrounded, etc.), defaults to `false`
 * @property useLifecycleObserver enables the use of LifecycleObserver to track Application lifecycle events. Defaults to `false`.
 * @property trackDeepLinks automatically track [Deep link][https://developer.android.com/training/app-links/deep-linking] opened based on intents, defaults to `false`
 * @property flushAt count of events at which we flush events, defaults to `20`
 * @property flushInterval interval in seconds at which we flush events, defaults to `30 seconds`
 * @property defaultSettings Settings object that will be used as fallback in case of network failure, defaults to empty
 * @property autoAddSegmentDestination automatically add SegmentDestination plugin, defaults to `true`
 * @property apiHost set a default apiHost to which Segment sends events, defaults to `api.segment.io/v1`
 */

data class Configuration(
    val writeKey: String,
    val application: Any? = null,
//    val storageProvider: StorageProvider,
    val collectDeviceId: Boolean = false,
    val trackApplicationLifecycleEvents: Boolean = false,
    val useLifecycleObserver: Boolean = false,
    val trackDeepLinks: Boolean = false,
    val flushAt: Int = 20,
    val flushInterval: Int = 30,
//    val defaultSettings: Settings = Settings(),
//    val autoAddSegmentDestination: Boolean = true,
    val apiHost: String? = null,
//    val cdnHost: String? = null
) {
    constructor(writeKey: WriteKey, context: Any? = null) : this(writeKey = writeKey.keyForPlatform(), application = context)
}
