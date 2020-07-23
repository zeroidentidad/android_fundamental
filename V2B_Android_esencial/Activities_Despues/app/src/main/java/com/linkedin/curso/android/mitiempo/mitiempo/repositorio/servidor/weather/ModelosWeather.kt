package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather

import android.os.Parcel
import android.os.Parcelable

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
}