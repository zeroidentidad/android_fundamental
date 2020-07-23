package com.linkedin.curso.android.mitiempo.mitiempo.utils

import java.io.IOException


class Utils {
    companion object {
        @Throws(IOException::class)
        fun capitalizar(string: String): String {
            val s1 = string.substring(0, 1).toUpperCase()
            return s1 + string.substring(1)
        }
    }

}