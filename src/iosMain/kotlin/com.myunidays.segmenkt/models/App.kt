package com.myunidays.segmenkt.models

import platform.Foundation.NSBundle
import com.myunidays.segmenkt.ApplicationContext

actual fun App.Companion.create(context: ApplicationContext?): App? = NSBundle.mainBundle.bundleIdentifier?.let { bundleIdentifier ->
    App(
        name = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleName") as String,
        namespace = bundleIdentifier,
        version = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as String,
        build = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as String
    )
}
