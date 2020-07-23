package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

import android.app.Fragment
import android.content.Context
import android.widget.Toast
import com.linkedin.curso.android.mitiempo.mitiempo.other.examples.javacode.ArtistaJava
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L

class Examples {
    fun kotlinVsJava() {
        /**
         * 1. Más expresivo
         *@see ArtistaJava.java
         *@see ArtistaKotlin.kt
         **/
        val artistaJava = ArtistaJava(1, "pepe", "http://url.es", "descripcion")
        artistaJava.descripcion = "descripcion 2"
        val artistaKotlin = ArtistaKotlin(2, "Juan", "http://url.es", "Otra descripcion")
        artistaKotlin.nombre = "Jacinto"
        L.d("Java:: $artistaJava")
        L.d("Kotlin:: $artistaKotlin")

        /**
         * 2. Más seguro -> Null safe
         * */
        // No compila:: Artista no puede ser null, descomenta si quieres ver que pasa
        // var notNullArtistaJava: ArtistaKotlin = null;
        // Artista puede ser null
        // var artista: ArtistaKotlin? = null
        var artista = ArtistaKotlin(3, "Luis", "http://url.es", "desc")
        // No compila ya que artista puede ser null, descomenta si quieres ver que pasa
        // artista.descripcion = "descripcionConNull"
        // Se ejecuta siempre que artista no sea null
        artista.descripcion = "descripcionSinNull"
        // No necesitamos hacer este código si hemos llamado al operador de null anteriormente
        if (artista != null) {
            artista.descripcion = "descripcionSinNullConCodigo"
        }
        // Solo usar si estamos seguros que no es nulo ya que genera una excepción en otro caso
        artista.descripcion = "descripcionQueGeneraExcepcionSiEsNulo"
        L.d("Kotlin-Null safe:: $artista")

        /**
         * 3. Es funcional -> Conceptos de la progamación funcional
         * por ejemplo: view.setOnClickListerner{toast("Hello world!")}
         * */

        /**
         * 4. Usa extension de funciones
         * @see Fragment.toastExample
         * */

        /**
         * 5. Altamente interoperable.
         * Como se ha visto en este ejemplo, hemos trabajado con clases java sin ningún problema
         * */
    }

    fun diferenciasEntreVarVsVar() {
        // var vs val
        /**
         * var -> variable en el tiempo, puedes hacer lectura y escritura de la variable
         * val -> el valor no se modifica, es un valor inmutable
         * */

        var variable = "Soy una variable"
        variable += "Y cambio en el tiempo"
        L.d("Mi variable $variable")

        val valor = "Yo no cambio"
        // Descomenta si quieres ver que pasa
        // valor = "Por mucho que lo intentes"
        L.d("Mi valor $valor")
    }

    fun Fragment.toastExample(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    fun buclesKotlin() {
        // Bucle for
        for (num in 1..5) {
            L.d("mi Número: $num")
        }
        // Bucle While
        var dia = 1
        L.d("Empieza la semana")
        while (dia < 6) {
            if (dia == 1) {
                L.d("$dia día trabajando")
            } else {
                L.d("$dia días trabajando")
            }

            dia++ // Actualizamos la condición
        }
        L.d("Menos mal que tenemos el finde")
        // Bucle do..while
        var numero = 1
        do {
            L.d("Mi num en while")
            numero++
        } while (numero !in 1..10)
        // Sentencia continue:: Con continue podemos hacer que se deje de ejecutarse el código dentro del bucle y que pase a la siguiente iteración
        for (num in 1..10) {
            if (num % 2 == 0) {
                continue
            }
            L.d("continue -> $num ")
        }
        // Sentencia break:: con break se sale completamente aunque aún no se haya cumplido la condición para que termine
        for (num in 1..10) {
            if (num % 5 == 0) {
                break
            }
            L.d("break -> $num ")
        }
    }

    fun condicionalesKotlin() {
        // If
        // Uso simple
        val a = 9
        val b = 7
        var result1: Int = -1

        // Caso simple
        if (a < b) result1 = b
        L.d("caso simple -> $result1 ")
        // Caso else
        var result2: Int
        if (a > b) {
            result2 = a
        } else {
            result2 = b
        }
        L.d("caso else -> $result2 ")
        // As expression
        val result3 = if (a > b) a else b
        L.d("Como una expresión -> $result3")

        // When
        var miWhen = 3
        when (miWhen) {
            1 -> L.d("x == 1")
            2 -> L.d("x == 2")
            else -> {
                L.d("la variable ni es 1 ni 2 :-)")
            }
        }
    }

    fun clasesHerencias() {
        val miPersona = Persona("Juan", "Loto", 30)
        val miEmpleado = Empleado("María", "Primitiva", 32, 1L)

        L.d("Mi Persona ${miPersona.imprimir()}")
        L.d("Mi Empleado ${miEmpleado.imprimir()}")
    }

    fun interfacesKotlin() {
        val miPersona = Persona("Juan", "Loto", 30)
        val miDeportista = Deportista("Rafa", "Nadal", 30, "tenis")

        L.d("Mi Persona ${miPersona.imprimir()}")
        L.d("Mi Deportista: ${miDeportista.imprimir()}")
        L.d("Mi Deportista con deporte: ${miDeportista.nombreDeportistaConDeporte()}")
        L.d("Mi Deportista con deporte sin espacios: ${miDeportista.nombreDeportistaConDeporte(false)}")
        L.d("Mi Deportista con deporte separador: ${miDeportista.nombreDeportistaConDeporte("*")}")
    }
}

data class ArtistaKotlin(
        var id: Long,
        var nombre: String,
        var url: String,
        var descripcion: String
)
