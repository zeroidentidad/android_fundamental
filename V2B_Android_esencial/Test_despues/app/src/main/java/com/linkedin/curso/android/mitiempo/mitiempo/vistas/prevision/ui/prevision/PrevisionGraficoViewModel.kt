package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather

class PrevisionGraficoViewModel : ViewModel() {

    var mDatosGrafico = ArrayList<Entry>()
    var mPrevision: ModelosWeather.Prevision? = null

    fun generarDatosGrafico(prevision: ModelosWeather.Prevision?) {
        mPrevision = prevision
        mDatosGrafico = ArrayList()

        var contador = 1F
        mPrevision?.list?.forEach {
            mDatosGrafico.add(Entry(contador++, it.main.temp_max))
        }

    }
}
