package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

interface IDeportista {
    fun nombreDeportistaConDeporte(): String
    fun nombreDeportistaConDeporte(separador: String): String
    fun nombreDeportistaConDeporte(conEspacios: Boolean): String
}