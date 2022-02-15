package com.myunidays.segmenkt

import android.content.Context
import java.util.concurrent.TimeUnit

actual typealias Configuration = com.myunidays.segmenkt.models.Configuration

actual fun Configuration(writeKey: WriteKey, context: Any?) =
    com.myunidays.segmenkt.models.Configuration(writeKey.keyForPlatform(), context as Context)

actual fun setupWithConfiguration(configuration: Configuration): Analytics {
    val analytics = com.segment.analytics.Analytics.Builder(configuration.context, configuration.writeKey)
        .flushInterval(1, TimeUnit.SECONDS)
        .recordScreenViews()
        .build()
    com.segment.analytics.Analytics.setSingletonInstance(analytics)
    return analytics
}

actual typealias Analytics = com.segment.analytics.Analytics

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

actual fun Analytics.shared(context: Any?) = Analytics.with(context as Context?)

