package com.myunidays.segmenkt

import android.content.Context
import com.segment.analytics.Properties
import com.segment.analytics.Traits
import java.util.concurrent.TimeUnit

actual class Analytics internal constructor(val android: com.segment.analytics.Analytics) {

    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            val analytics = com.segment.analytics.Analytics.Builder(configuration.application as Context, configuration.writeKey)
                .flushInterval(1, TimeUnit.SECONDS)
                .build()
            com.segment.analytics.Analytics.setSingletonInstance(analytics)
            return Analytics(analytics)
        }
        actual fun shared(context: Any?): Analytics =
            Analytics(com.segment.analytics.Analytics.with(context as? Context))
    }

    actual fun track(name: String, properties: Map<Any?, *>?) =
        android.track(name, Properties().apply {
            properties?.forEach { property ->
                (property.key as? String)?.let { putValue(it, property.value) }
            }
        })

    actual fun identify(userId: String, traits: Map<Any?, *>?) =
        android.identify(userId, Traits().apply {
            traits?.forEach { trait ->
                (trait.key as? String)?.let { putValue(it, trait.value) }
            }
        }, null)

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?
    ) = android.screen(screenTitle, Properties().apply {
        properties?.forEach { property ->
            (property.key as? String)?.let { putValue(it, property.value) }
        }
    })

    actual fun group(groupId: String, traits: Map<Any?, *>?) =
        android.group(groupId, Traits().apply {
            traits?.forEach { trait ->
                (trait.key as? String)?.let { putValue(it, trait.value) }
            }
        })

}
