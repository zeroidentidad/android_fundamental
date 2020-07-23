package com.linkedin.curso.android.mitiempo.mitiempo.other.examples

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
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
        primerNumero = 5
        segundoNumero = 7
    }

    @Test
    fun suma() {
        assertEquals("El resultado debe ser " + (primerNumero + segundoNumero), clase2test?.suma(primerNumero, segundoNumero), primerNumero + segundoNumero)
    }

    @Test
    fun resta() {
        val primero = 3
        val segundo = 11
        assertTrue("El resultado debe ser " + (primero - segundo), (primero - segundo) == clase2test?.resta(primero, segundo))
    }

    @Test
    fun multiplicacion() {
        assertNotNull("El resultado no debe ser nulo", clase2test?.multiplicacion(primerNumero, segundoNumero))
    }

    @Test
    fun division() {
        assertEquals("El resultado debe ser " + (primerNumero / segundoNumero), clase2test?.division(primerNumero, segundoNumero), (primerNumero / segundoNumero))
        Mockito.doNothing().`when`(clase2test)?.dividimosPorCero()
        try {
            clase2test?.division(primerNumero, 0)
            Assert.fail("MAL esperabamos una excepcion")
        } catch (e: Exception) {
            verify(clase2test, times(1))?.dividimosPorCero()
        }
    }

    @Test
    fun contarEmpleados() {
        val listaPersona = ArrayList<Persona>()

        for (num in 1..7) {
            empleado?.let { listaPersona.add(it) }
        }

        for (num in 1..3) {
            deportista?.let { listaPersona.add(it) }
        }

        assertEquals("el resultado debe ser 7", clase2test?.contarEmpleados(listaPersona), 7)
    }
}