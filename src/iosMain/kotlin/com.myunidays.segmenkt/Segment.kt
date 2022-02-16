package com.myunidays.segmenkt

actual class Analytics internal constructor(val ios: cocoapods.Analytics.SEGAnalytics) {

    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            val config = cocoapods.Analytics.SEGAnalyticsConfiguration.configurationWithWriteKey(configuration.writeKey)
            config.flushInterval = 1.0
            cocoapods.Analytics.SEGAnalytics.setupWithConfiguration(config)
            return shared(null)
        }

        actual fun shared(context: Any?): Analytics =
            Analytics(cocoapods.Analytics.SEGAnalytics.sharedAnalytics())
    }

    actual fun track(name: String, properties: Map<Any?, *>?) = ios.track(name, properties)

    actual fun identify(userId: String, traits: Map<Any?, *>?) = ios.identify(userId, traits)

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?
    ) = ios.screen(screenTitle, properties)

    actual fun group(groupId: String, traits: Map<Any?, *>?) = ios.group(groupId, traits)

}
