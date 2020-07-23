package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

// Los getter y los setter se auto generan
open class Persona(var nombre: String, var apellidos: String, var edad: Int) {
    // se puede jugar con los parámetros para contruir otros nuevos
    val apellidosNombre = "$apellidos, $nombre"
    var nombreApellidos: String? = null
    var mayorEdad: Boolean? = null

    // Definición del constructor principal
    init {
        nombreApellidos = "$nombre, $apellidos"
        mayorEdad = edad >= 18
    }

    // Funciones para nosotros y nuestros hijos
    open fun imprimir()
            : String {
        return "Nombre: $apellidosNombre, edad: $edad, nombre: $nombre, apellidos: $apellidos"
    }
}