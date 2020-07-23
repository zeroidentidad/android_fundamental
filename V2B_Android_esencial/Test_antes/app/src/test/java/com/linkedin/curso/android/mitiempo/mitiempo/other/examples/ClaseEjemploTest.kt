package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ClaseEjemploTest {
    @Spy
    private var clase2test: ClaseEjemplo? = null

    @Mock
    private var empleado: Empleado? = null
    @Mock
    private var deportista: Deportista? = null

    private var primerNumero: Int = 0
    private var segundoNumero: Int = 0


    @Before
    fun setUp() {
        // Todas las acciones que necesitemos para preparar el conjunto de los test
        // TODO:: Iniciar los elementos para trabajar
    }

    @Test
    fun suma() {
        // TODO:: Generar una prueba para comprobar el resultado de manera sencilla
    }

    @Test
    fun resta() {
        // TODO:: Generar una prueba con variables internas al test
    }

    @Test
    fun multiplicacion() {
        // TODO:: Generar una prueba para saber que el valor devuelto no es nulo
    }

    @Test
    fun division() {
        assertEquals("El resultado debe ser " + (primerNumero / segundoNumero), clase2test?.division(primerNumero, segundoNumero), (primerNumero / segundoNumero))

        // TODO:: Generar una prueba para saber si se está ejecutando una excepción

        // TODO:: no hacer nada en una funcion que no estamos probando, en este momento no nos interesa probar
    }

    @Test
    fun contarEmpleados() {

        // TODO:: incluir en el arraylist los datos mock

        // TODO:: Verificar que todo ha ido segun lo esperado

    }
}