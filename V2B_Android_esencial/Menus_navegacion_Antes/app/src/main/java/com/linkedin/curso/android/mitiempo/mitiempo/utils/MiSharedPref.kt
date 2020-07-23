package com.linkedin.curso.android.mitiempo.mitiempo.utils

import android.content.Context

class MiSharedPref(val context: Context) {
    // TODO: Variable para manejar la lectura del fichero de preferencias
    // TODO:: Variable para manejar la escritura del fichero de preferencias

    companion object {
        private const val NOMBRE = "FicheroPreferencias"
        // TODO: Keys para usar dentro de la aplicacion
        const val LONGITUD_KEY = "Longitud"
        const val LATITUD_KEY = "Latitud"
    }

    fun guardarString(key: String, dato: String) {
        // TODO:: incluir el valor dentro del fichero de preferencias
    }

    fun recogerString(key: String, default: String): String {
        // TODO:: Recoger del fichero de preferencias el valor
        return ""
    }


}