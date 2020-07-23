package com.linkedin.curso.android.mitiempo.mitiempo.observers

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.OpenWeatherServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L.Companion.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LocalizacionObserver(var activity: Activity) : LifecycleObserver {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var disposable: Disposable? = null

    private val openWeatherServicio by lazy {
        OpenWeatherServicio.crear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeListener() {
        if (ContextCompat.checkSelfPermission(activity.baseContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Perdirá los permisos necesarios al usuario mediante un diálogo estándar del sistema
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    L.d("Latitud: ${location?.latitude}")
                    L.d("Longitud: ${location?.longitude}")

                    if (location != null) {
                        disposable = openWeatherServicio.tiempoActual(location.latitude, location.longitude)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        { temperatura -> L.d("Temperatura $temperatura") },
                                        { error -> L.d("Error ${error.message}") })
                    }
                }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseListener() {
        d("Estamos en el listener de onPause")
        disposable?.dispose()
    }
}