package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather

object ModelosWeather {
    data class ResultadoTiempoActual(val main: Main, val weather: List<Weather>)

    data class Main(val temp: Float,
                    val humidity: Int,
                    val pressure: Int,
                    val temp_min: Float,
                    val temp_max: Float)

    data class Weather(val id: Int,
                       val main: String,
                       val description: String,
                       val icon: String)

    // TODO:: Crear un tipo de datos para poder pasar datos complejos entre actividades
    data class InformacionImprescindible(val temp: Float, val description: String)
}