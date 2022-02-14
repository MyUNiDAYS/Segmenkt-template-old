package com.myunidays.segmenkt

actual typealias Configuration = com.segment.analytics.kotlin.core.Configuration

actual fun Config(writeKey: String, context: Any?): Configuration {
    return com.segment.analytics.kotlin.core.compat.ConfigurationBuilder(writeKey)
        .setApplication(context)
        .build()
}

actual typealias Analytics = com.segment.analytics.kotlin.core.Analytics

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
    return com.segment.analytics.kotlin.core.Analytics(configuration)
}
