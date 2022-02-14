package com.myunidays.segmenkt

import cocoapods.Analytics.SEGAnalytics
import cocoapods.Analytics.SEGAnalyticsConfiguration

actual typealias Configuration = SEGAnalyticsConfiguration

actual fun Config(writeKey: String, context: Any?): Configuration {
    return SEGAnalyticsConfiguration.configurationWithWriteKey(writeKey)
}

actual typealias Analytics = SEGAnalytics

actual fun Analytics.track(name: String, properties: Map<Any?, *>?) {
    track(name, properties)
}

actual fun Analytics.identify(userId: String, traits: Map<Any?, *>?) {
    identify(userId, traits)
}

actual fun Analytics.screen(screenTitle: String, properties: Map<Any?, *>?, category: String) {
    screen(screenTitle, properties, category)
}

actual fun Analytics.group(groupId: String, traits: Map<Any?, *>?) {
    group(groupId, traits)
}

actual fun setupWithConfiguration(configuration: Configuration): Analytics {
    SEGAnalytics.setupWithConfiguration(configuration)
    return SEGAnalytics.sharedAnalytics()
}

fun Analytics.shared(): Analytics {
    return SEGAnalytics.sharedAnalytics()
}
