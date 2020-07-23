package com.linkedin.curso.android.mitiempo.mitiempo.workers

import androidx.work.Worker
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L

class SubirFicheroWorker : Worker() {

    override fun doWork(): WorkerResult {
        return try {
            L.d("Estamos Subiendo un fichero :-P")
            WorkerResult.SUCCESS
        } catch (ex: Exception) {
            WorkerResult.FAILURE
        }
        // WorkerResult.RETRY -> Le dice al WorkManager que intente otra vez la tarea
    }
}