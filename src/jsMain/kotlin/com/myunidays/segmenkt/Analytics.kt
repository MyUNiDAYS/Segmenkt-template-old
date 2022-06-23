package com.myunidays.segmenkt

actual class Analytics {
    actual companion object {
        actual fun setupWithConfiguration(configuration: Configuration): Analytics {
            Log.d("JS Todo")
            return shared(null)
        }

        actual fun shared(context: Any?): Analytics = Analytics()
    }

    init {
        Log()
    }

    actual fun alias(userId: String, options: Map<Any?, *>?) {
        Log.d("JS Todo")
    }

    actual fun track(name: String, properties: Map<Any?, *>?, options: Map<Any?, *>?) {
        Log.d("JS Todo")
    }

    actual fun identify(userId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) {
        Log.d("JS Todo")
    }

    actual fun screen(
        screenTitle: String,
        properties: Map<Any?, *>?,
        options: Map<Any?, *>?
    ) {
        Log.d("JS Todo")
    }

    actual fun group(groupId: String, traits: Map<Any?, *>?, options: Map<Any?, *>?) {
        Log.d("JS Todo")
    }

    actual fun reset() {
        Log.d("JS Todo")
    }
}
