package com.linkedin.curso.android.jetpack.architecturecomponents.basico.lifecycle

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*


class CronometroViewModele : ViewModel() {
    var startTime: Long = 0L

    val tiempoTranscurrido = MutableLiveData<Long>()

    private val SEG: Long = 1000L
    private var tiempoInicial: Long = 0

    init {
        tiempoInicial = SystemClock.elapsedRealtime()
        val timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - tiempoInicial) / 1000
                tiempoTranscurrido.postValue(newValue);
            }
        }, SEG, SEG)
    }
}