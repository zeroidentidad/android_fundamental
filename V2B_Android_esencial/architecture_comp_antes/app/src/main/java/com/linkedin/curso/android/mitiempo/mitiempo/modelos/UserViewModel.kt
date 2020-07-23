package com.linkedin.curso.android.mitiempo.mitiempo.modelos

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.MiTiempoRoomDatabase


class UserViewModel : ViewModel() {
    private lateinit var mBBDD: MiTiempoRoomDatabase
    private var mCurrentName: MutableLiveData<String> = MutableLiveData()
    var contador: Int = 0


    fun setUp(aplicacion: Application) {
        // TODO:: Generar la base de datos
        // TODO:: Objeto vivo, podemos observar los cambios que se van produciendo

    }

    fun getCurrentName(): MutableLiveData<String> {
        return mCurrentName
    }

    fun insertarDatosUsuario(usuario: Unit) {
        // TODO:: Llamada a la base de datos para insertar el nuevo usuario

    }

    fun actualizarFicheroUsuario() {
        // TODO:: Iniciar el WorkManager para poder trabajar
        // TODO:: Generar las constraint para trabajar, en este caso queremos que se ejecute cuando estemos conectados
        // TODO:: Iniciamos el worker para comprimir un archivo
        // TODO:: Iniciamos el worker para subir el archivo
        // TODO:: Concatenar los workers uno tras otro siguiendo un orden definido

    }

}