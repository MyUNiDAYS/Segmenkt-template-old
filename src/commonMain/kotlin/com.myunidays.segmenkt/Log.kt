package com.myunidays.segmenkt

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class Log {
    companion object {
        fun d(message: String) = Napier.d(message)
        fun i(message: String) = Napier.i(message)
        fun v(message: String) = Napier.v(message)
        fun e(message: String) = Napier.e(message)
        fun w(message: String) = Napier.w(message)
    }

    init {
        Napier.base(DebugAntilog())
    }
}

interface Logger {
    fun log(message: String)
}
