package com.myunidays.segmenkt

actual class Analytics internal constructor(val ios: cocoapods.Analytics.SEGAnalytics) {

    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            val analyticsConfig = cocoapods.Analytics.SEGAnalyticsConfiguration.configurationWithWriteKey(configuration.writeKey)
            analyticsConfig.trackApplicationLifecycleEvents = configuration.trackApplicationLifecycleEvents
            analyticsConfig.flushInterval = configuration.flushInterval.toDouble()
            analyticsConfig.flushAt = configuration.flushAt.toULong()
            analyticsConfig.trackDeepLinks = configuration.trackDeepLinks
            cocoapods.Analytics.SEGAnalytics.setupWithConfiguration(analyticsConfig)
            return shared(null)
        }

        actual fun shared(context: Any?): Analytics =
            Analytics(cocoapods.Analytics.SEGAnalytics.sharedAnalytics())
    }

    init {
        Log()
    }

    actual fun alias(userId: String) = ios.alias(userId)

    actual fun track(name: String, properties: Map<Any?, *>?) = ios.track(name, properties)
        .also { Log.d("Segment: Track $name: $properties") }

    actual fun identify(userId: String, traits: Map<Any?, *>?) = ios.identify(userId, traits)
        .also { Log.d("Segment: Identify $userId: $traits") }

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?
    ) = ios.screen(screenTitle, properties)
        .also { Log.d("Segment: Screen $screenTitle: $properties") }

    actual fun group(groupId: String, traits: Map<Any?, *>?) = ios.group(groupId, traits)
        .also { Log.d("Segment: Group $groupId: $traits") }

    actual fun reset() {
        ios.reset().also { Log.d("Segment: Reset") }
    }
}
