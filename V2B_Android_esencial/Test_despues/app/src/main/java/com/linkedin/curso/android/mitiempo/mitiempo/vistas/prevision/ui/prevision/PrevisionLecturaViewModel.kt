package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModel
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather

class PrevisionLecturaViewModel : ViewModel() {
    var prevision: ModelosWeather.Prevision? = null
}
