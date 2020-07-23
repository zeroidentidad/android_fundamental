package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather

import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig
import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig.OPEN_WEATHER_API_KEY
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherServicio {
    // API OpenWeather ayuda: https://openweathermap.org/forecast5#data
    @GET("weather")
    fun tiempoActual(@Query("lat") latitud: Double,
                     @Query("lon") longitud: Double,
                     @Query("APPID") appid: String = OPEN_WEATHER_API_KEY,
                     @Query("units") units: String = "metric",
                     @Query("lang") lang: String = "es"): Observable<ModelosWeather.ResultadoTiempoActual>

    // TODO:: Generar el nuevo EndPoint para recoger los datos de prevision

    companion object {
        fun crear(): OpenWeatherServicio {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.weather_api)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(OpenWeatherServicio::class.java)
        }
    }
}