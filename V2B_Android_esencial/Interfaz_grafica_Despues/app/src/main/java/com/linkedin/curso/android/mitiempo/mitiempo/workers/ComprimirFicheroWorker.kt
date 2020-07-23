package com.linkedin.curso.android.mitiempo.mitiempo.workers

import androidx.work.Worker
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L

class ComprimirFicheroWorker : Worker() {

    override fun doWork(): WorkerResult {
        return try {
            L.d("Estamos Comprimiendo un fichero :-)")
            WorkerResult.SUCCESS
        } catch (ex: Exception) {
            WorkerResult.FAILURE
        }
        // WorkerResult.RETRY -> Le dice al WorkManager que intente otra vez la tarea
    }
}