package com.linkedin.curso.android.jetpack.architecturecomponents.basico.lifecycle

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import kotlinx.android.synthetic.main.activity_cronometro.*


class CronometroActivity : AppCompatActivity(), LifecycleOwner {
    private lateinit var cronometroViewModel: CronometroViewModele

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronometro)

        // El ViewModelProviders proporciona un nuevo ViewModel en el caso de que no existiera
        cronometroViewModel = ViewModelProviders.of(this).get(CronometroViewModele::class.java)

        suscribirnos()

        lifecycle.addObserver(ConometroLifecycleObserver())

    }

    private fun suscribirnos() {
        val tiempoTranscurridoObserver = Observer<Long> { aLong ->
            val nuevoTexto = this@CronometroActivity.resources.getString(R.string.segundos, aLong)
            cronometro_view.text = nuevoTexto
        }
        cronometroViewModel.tiempoTranscurrido.observe(this, tiempoTranscurridoObserver)
    }

    fun usoViewModel() {
        if (cronometroViewModel.startTime == 0L) {
            // En el caso de que el viewModel no contenga datos
            val startTime = SystemClock.elapsedRealtime()
            cronometroViewModel.startTime = startTime
            cronometro_view.base = startTime
        } else {
            // En otro caso el viewModel ya fue creado
            cronometro_view.base = cronometroViewModel.startTime
        }

        cronometro_view.start()
    }
}
