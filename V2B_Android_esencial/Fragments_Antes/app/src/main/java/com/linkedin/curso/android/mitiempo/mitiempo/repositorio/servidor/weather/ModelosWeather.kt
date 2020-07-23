package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather

import android.os.Parcel
import android.os.Parcelable

object ModelosWeather {
    data class ResultadoTiempoActual(val main: Main, val weather: List<Weather>)
    // TODO: Crear el nuevo Modelo para recoger la prevision

    data class Main(val temp: Float,
                    val humidity: Float,
                    val pressure: Float,
                    val temp_min: Float,
                    val temp_max: Float)

    data class Weather(val id: Int,
                       val main: String,
                       val description: String,
                       val icon: String)

    data class City(val id: Int,
                    val name: String,
                    var coords: Coords)

    data class Coords(val lon: Float,
                      val lat: Float)

    data class Lista(val dt: Long,
                     var main: Main,
                     var weather: List<Weather>,
                     var dt_txt: String)

    data class InformacionImprescindible(val temp: Float, val description: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readFloat(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeFloat(temp)
            parcel.writeString(description)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<InformacionImprescindible> {
            override fun createFromParcel(parcel: Parcel): InformacionImprescindible {
                return InformacionImprescindible(parcel)
            }

            override fun newArray(size: Int): Array<InformacionImprescindible?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class PosicionUsuario(val longitud: Double, val latitud: Double) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readDouble(),
                parcel.readDouble())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeDouble(longitud)
            parcel.writeDouble(latitud)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<PosicionUsuario> {
            override fun createFromParcel(parcel: Parcel): PosicionUsuario {
                return PosicionUsuario(parcel)
            }

            override fun newArray(size: Int): Array<PosicionUsuario?> {
                return arrayOfNulls(size)
            }
        }
    }
}