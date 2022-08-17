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
                .tag(if (configuration.tag.isNullOrBlank()) configuration.writeKey else configuration.tag + "-" + configuration.writeKey)
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

    actual fun alias(userId: String, options: Map<Any?, *>?) =
        android.alias(
            userId,
            Options().apply {
                options?.forEach { property ->
                    (property.key as? String)?.let { putContext(it, property.value) }
                }
            }
        )

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
        )

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
        )

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
    )

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
        )

    actual fun reset() {
        android.reset()
    }
}
