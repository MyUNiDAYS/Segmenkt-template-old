package com.myunidays.segmenkt

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

internal var testing: Boolean = false

class Log {
    companion object {
//        fun d(message: String) = if (testing) println(message) else Napier.d(message)
//        fun i(message: String) = if (testing) println(message) else Napier.i(message)
//        fun v(message: String) = if (testing) println(message) else Napier.v(message)
//        fun e(message: String) = if (testing) println(message) else Napier.e(message)
//        fun w(message: String) = if (testing) println(message) else Napier.w(message)

        fun d(message: String) = Napier.d(message)
        fun i(message: String) = Napier.i(message)
        fun v(message: String) = Napier.v(message)
        fun e(message: String) = Napier.e(message)
        fun w(message: String) = Napier.w(message)

        fun close() = Napier.takeLogarithm()
    }

    init {
        Napier.base(DebugAntilog())
    }
}

interface Logger {
    fun log(message: String)
}
