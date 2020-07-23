package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

import com.linkedin.curso.android.mitiempo.mitiempo.utils.L

class ClaseEjemplo {
    fun suma(x: Int, y: Int): Int {
        return x + y
    }

    fun resta(x: Int, y: Int): Int {
        return x - y
    }

    fun multiplicacion(x: Int, y: Int): Int {
        return x * y
    }

    fun division(x: Int, y: Int): Int {
        if (y == 0) {
            dividimosPorCero()
            throw NumberFormatException("Division por 0 no admitida")
        } else {
            return x / y
        }
    }

    fun contarEmpleados(personas: List<Persona>): Int {
        var count = 0
        personas.forEach {
            if (it is Empleado) {
                count++
            }
        }

        return count
    }

    fun dividimosPorCero() {
        L.d("Estamos dividiendo por 0, eso no es correcto")
    }
}