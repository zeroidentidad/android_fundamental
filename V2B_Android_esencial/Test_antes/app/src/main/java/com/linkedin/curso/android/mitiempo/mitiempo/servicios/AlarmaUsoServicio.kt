package com.linkedin.curso.android.mitiempo.mitiempo.servicios

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import java.util.*

class AlarmaUsoServicio : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        // Si el servicio fuera de enlace devolveríamos el Binder en esta funcion
        // Función: otro componente desea enlazarse con el servicio  llamando a bindService()
        L.d("Función onBind")
        return null
    }

    override fun onCreate() {
        // El sistema llama a este método cuando se crea el servicio por primera vez
        super.onCreate()
        L.d("Función onCreate")
    }

    override fun onDestroy() {
        // El sistema llama a este método cuando el servicio ya no se utiliza y se lo está destruyendo
        super.onDestroy()
        L.d("Función onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // El sistema llama a este método cuando otro componente, solicita que se inicie el servicio, llamando a startService().
        Thread(Runnable {
            val calendarAlarmaUso = Calendar.getInstance()
            calendarAlarmaUso.add(Calendar.SECOND, 30)
            var contadorSegundo = 1
            do {
                Thread.sleep(1000)
                L.d("Llevamos dormidos " + contadorSegundo++)
            } while (calendarAlarmaUso.timeInMillis > Calendar.getInstance().timeInMillis)
            L.d("Llevas 30 segundos dentro de la app ya es suficiente")

            stopSelf()
        }).start()
        return super.onStartCommand(intent, flags, startId)
    }
}