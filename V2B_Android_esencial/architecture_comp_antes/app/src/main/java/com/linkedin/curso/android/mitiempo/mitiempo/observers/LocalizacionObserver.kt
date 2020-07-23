package com.linkedin.curso.android.mitiempo.mitiempo.observers

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L.Companion.d


class LocalizacionObserver(var activity: Activity) : LifecycleObserver {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeListener() {
        // TODO:: Comprobar los permisos
        // TODO:: Recoger la última actualización de la localización
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseListener() {
        d("Estamos en el listener de onPause")
    }
}