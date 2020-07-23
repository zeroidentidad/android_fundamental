package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

class Deportista(nombre: String, apellidos: String, edad: Int, var deporte: String) :
        Persona(nombre, apellidos, edad),
        IDeportista {
    override fun nombreDeportistaConDeporte(conEspacios: Boolean): String {
        if (!conEspacios) {
            return nombreDeportistaConDeporte().replace(" ", "")
        }
        return nombreDeportistaConDeporte()
    }

    override fun nombreDeportistaConDeporte(separador: String): String {
        return nombreDeportistaConDeporte().replace(" ", separador)
    }

    override fun nombreDeportistaConDeporte(): String {
        return "${imprimir()} y su deporte es $deporte"
    }
}