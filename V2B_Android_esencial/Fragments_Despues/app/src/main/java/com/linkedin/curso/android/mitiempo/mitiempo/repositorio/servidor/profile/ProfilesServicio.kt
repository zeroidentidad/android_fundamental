package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile

import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfilesServicio {
    @GET("api")
    fun getProfile(@Query("nat") nacionalidad: String): Observable<ModelosProfile.Resultado>

    companion object {
        fun crear(): ProfilesServicio {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.profile_api)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ProfilesServicio::class.java)
        }
    }
}