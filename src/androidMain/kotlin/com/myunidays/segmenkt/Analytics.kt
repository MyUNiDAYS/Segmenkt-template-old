package com.myunidays.segmenkt

import android.content.Context
import com.segment.analytics.Options
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

    actual fun alias(userId: String, options: Map<Any?, *>?) =
        android.alias(
            userId,
            Options().apply {
                options?.forEach { property ->
                    (property.key as? String)?.let { putContext(it, property.value) }
                }
            }
        ).also { Log.d("Segment: Alias $userId $options") }

    actual fun track(name: String, properties: Map<Any?, *>?, options: Map<Any?, *>?) =
        android.track(
            name,
            Properties().apply {
                properties?.forEach { property ->
                    (property.key as? String)?.let { putValue(it, property.value) }
                }
            },
            Options().apply {
                options?.forEach { property ->
                    (property.key as? String)?.let { putContext(it, property.value) }
                }
            }
        ).also { Log.d("Segment: Track $name: $properties $options") }

    actual fun identify(userId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) =
        android.identify(
            userId,
            Traits().apply {
                traits?.forEach { trait ->
                    (trait.key as? String)?.let { putValue(it, trait.value) }
                }
            },
            Options().apply {
                options?.forEach { property ->
                    (property.key as? String)?.let { putContext(it, property.value) }
                }
            }
        ).also { Log.d("Segment: Identify $userId: $traits $options") }

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?,
        options: Map<Any?, *>?
    ) = android.screen(
        null,
        screenTitle,
        Properties().apply {
            properties?.forEach { property ->
                (property.key as? String)?.let { putValue(it, property.value) }
            }
        },
        Options().apply {
            options?.forEach { property ->
                (property.key as? String)?.let { putContext(it, property.value) }
            }
        }
    ).also { Log.d("Segment: Screen $screenTitle: $properties $options") }

    actual fun group(groupId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) =
        android.group(
            groupId,
            Traits().apply {
                traits?.forEach { trait ->
                    (trait.key as? String)?.let { putValue(it, trait.value) }
                }
            },
            Options().apply {
                options?.forEach { property ->
                    (property.key as? String)?.let { putContext(it, property.value) }
                }
            }
        ).also { Log.d("Segment: Group $groupId: $traits") }

    actual fun reset() {
        android.reset()
            .also { Log.d("Segment: Reset") }
    }
}
