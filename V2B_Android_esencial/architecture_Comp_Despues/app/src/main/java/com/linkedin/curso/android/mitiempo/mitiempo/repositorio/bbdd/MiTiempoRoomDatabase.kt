package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.daos.UsuarioDao
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.entidades.Usuario

@Database(entities = [(Usuario::class)], version = 1, exportSchema = true)
abstract class MiTiempoRoomDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        private var INSTANCE: MiTiempoRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MiTiempoRoomDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MiTiempoRoomDatabase::class.java, "mi_tiempo.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}