package com.linkedin.curso.android.mitiempo.mitiempo.utils

import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class L {
    private var NAME = "MI_TIEMPO"

    init {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)   // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Hides internal method calls up to offset. Default 5
                //.logStrategy(customLog)     // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(NAME)                      // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG_MODE
            }
        })
    }

    companion object {
        private var instance: L? = null

        fun d(message: String) {
            iniciarLog()
            Logger.d(message)
        }

        fun v(message: String) {
            iniciarLog()
            Logger.v(message)
        }

        private fun iniciarLog() {
            if (instance == null) {
                instance = L()
            }
        }

        fun w(message: String, e: Exception) {
            iniciarLog()
            Logger.w(message, e)
        }

        fun e(message: String, e: Exception) {
            iniciarLog()
            Logger.e(message, e)
        }
    }
}