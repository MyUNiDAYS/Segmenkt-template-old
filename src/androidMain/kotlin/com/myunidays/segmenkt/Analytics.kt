package com.myunidays.segmenkt

import android.content.Context
import com.segment.analytics.Properties
import com.segment.analytics.Traits
import java.util.concurrent.TimeUnit

actual class Analytics internal constructor(val android: com.segment.analytics.Analytics) {

    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            val analyticsConfig = com.segment.analytics.Analytics.Builder(configuration.application as Context, configuration.writeKey)
                .collectDeviceId(configuration.collectDeviceId)
                .experimentalUseNewLifecycleMethods(configuration.useLifecycleObserver)
                .flushInterval(configuration.flushInterval.toLong(), TimeUnit.SECONDS)
                .flushQueueSize(configuration.flushAt)
            if (configuration.trackDeepLinks) analyticsConfig.trackDeepLinks()
            if (configuration.trackApplicationLifecycleEvents) analyticsConfig.trackApplicationLifecycleEvents()
            configuration.apiHost?.let { analyticsConfig.defaultApiHost(it) }
            val analytics = analyticsConfig.build()
            com.segment.analytics.Analytics.setSingletonInstance(analytics)
            return Analytics(analytics)
        }
        actual fun shared(context: Any?): Analytics =
            Analytics(com.segment.analytics.Analytics.with(context as? Context))
    }

    init {
        Log()
    }

    actual fun alias(userId: String) = android.alias(userId)

    actual fun track(name: String, properties: Map<Any?, *>?) =
        android.track(
            name,
            Properties().apply {
                properties?.forEach { property ->
                    (property.key as? String)?.let { putValue(it, property.value) }
                }
            }
        ).also { Log.d("Segment: Track $name: $properties") }

    actual fun identify(userId: String, traits: Map<Any?, *>?) =
        android.identify(
            userId,
            Traits().apply {
                traits?.forEach { trait ->
                    (trait.key as? String)?.let { putValue(it, trait.value) }
                }
            },
            null
        )
            .also { Log.d("Segment: Identify $userId: $traits") }

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?
    ) = android.screen(
        screenTitle,
        Properties().apply {
            properties?.forEach { property ->
                (property.key as? String)?.let { putValue(it, property.value) }
            }
        }
    ).also { Log.d("Segment: Screen $screenTitle: $properties") }

    actual fun group(groupId: String, traits: Map<Any?, *>?) =
        android.group(
            groupId,
            Traits().apply {
                traits?.forEach { trait ->
                    (trait.key as? String)?.let { putValue(it, trait.value) }
                }
            }
        )
            .also { Log.d("Segment: Group $groupId: $traits") }

    actual fun reset() {
        android.reset()
            .also { Log.d("Segment: Reset") }
    }
}
