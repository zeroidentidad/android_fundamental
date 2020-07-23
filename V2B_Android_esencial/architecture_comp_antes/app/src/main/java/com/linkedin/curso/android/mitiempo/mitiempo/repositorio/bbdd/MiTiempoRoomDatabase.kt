package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd

import android.arch.persistence.room.RoomDatabase
import android.content.Context

abstract class MiTiempoRoomDatabase : RoomDatabase() {
    // TODO: definir que daos se van a usar

    // TODO: Definir la instancia de la base de datos
    // TODO:: Generar la instancia de la bbdd
    @Synchronized
    fun getInstance(context: Context): MiTiempoRoomDatabase? {
        return null
    }

    // TODO:: Destruir la instancia de la data base cuando sea necesario

}