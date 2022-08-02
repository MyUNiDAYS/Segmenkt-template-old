package com.myunidays.segmenkt

actual class Analytics {
    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            return shared(null)
        }

        actual fun shared(context: Any?): Analytics = Analytics()
    }

    actual fun alias(userId: String, options: Map<Any?, *>?) {
    }

    actual fun track(name: String, properties: Map<Any?, *>?, options: Map<Any?, *>?) {
    }

    actual fun identify(userId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) {
    }

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?,
        options: Map<Any?, *>?
    ) {
    }

    actual fun group(groupId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) {
    }

    actual fun reset() {
    }
}
