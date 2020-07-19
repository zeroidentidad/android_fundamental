package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(CompanieroEntity::class)], version = 1, exportSchema = true)
abstract class AplicacionRoomDatabase : RoomDatabase() {

    abstract fun companieroDao(): CompanieroDao

    companion object {
        private var INSTANCE: AplicacionRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AplicacionRoomDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AplicacionRoomDatabase::class.java, "miapp.db")
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