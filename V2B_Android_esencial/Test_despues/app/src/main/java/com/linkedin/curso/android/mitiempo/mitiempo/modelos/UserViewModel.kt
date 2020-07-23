package com.linkedin.curso.android.mitiempo.mitiempo.modelos

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.MiTiempoRoomDatabase
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.daos.estructuras.Usuario
import com.linkedin.curso.android.mitiempo.mitiempo.workers.ComprimirFicheroWorker
import com.linkedin.curso.android.mitiempo.mitiempo.workers.SubirFicheroWorker


class UserViewModel : ViewModel() {
    private lateinit var mBBDD: MiTiempoRoomDatabase
    private var mCurrentName: MutableLiveData<String> = MutableLiveData()
    var contador: Int = 0

    lateinit var mTodosUsuarios: LiveData<List<Usuario>>

    fun setUp(aplicacion: Application) {
        mBBDD = MiTiempoRoomDatabase.getInstance(aplicacion.applicationContext)!!
        mTodosUsuarios = mBBDD.usuarioDao().selectAll()
    }

    fun getCurrentName(): MutableLiveData<String> {
        return mCurrentName
    }

    fun insertarDatosUsuario(usuario: Usuario) {
        mBBDD.usuarioDao().insert(usuario)
    }

    fun actualizarFicheroUsuario() {
        val workManager = WorkManager.getInstance()
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val comprimirFicheroWorker = OneTimeWorkRequest.Builder(ComprimirFicheroWorker::class.java)
                .setConstraints(constraints)
                .build()
        val subirFicheroWorker = OneTimeWorkRequest.Builder(SubirFicheroWorker::class.java)
                .setConstraints(constraints)
                .build()
        workManager.beginWith(comprimirFicheroWorker).then(subirFicheroWorker).enqueue()

    }

}