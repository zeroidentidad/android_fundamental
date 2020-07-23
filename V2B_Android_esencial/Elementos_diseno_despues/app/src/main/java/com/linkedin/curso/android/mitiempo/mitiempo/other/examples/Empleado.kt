package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

class Empleado(nombre: String, apellidos: String, edad: Int, var id: Long) : Persona(nombre, apellidos, edad) {
    override fun imprimir(): String {
        return "id: $id, ${super.imprimir()}"
    }
}