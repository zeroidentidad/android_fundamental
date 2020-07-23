package com.linkedin.curso.android.mitiempo.mitiempo.utils

import android.content.Context

class MiSharedPref(val context: Context) {
    private val preferences = context.getSharedPreferences(NOMBRE, Context.MODE_PRIVATE)!!
    private val editor = preferences.edit()!!

    companion object {
        private const val NOMBRE = "FicheroPreferencias"
        const val LONGITUD_KEY = "Longitud"
        const val LATITUD_KEY = "Latitud"
    }

    fun guardarString(key: String, dato: String) {
        this.editor.putString(key, dato).apply()
    }

    fun recogerString(key: String, default: String): String {
        return this.preferences.getString(key, default)
    }

}