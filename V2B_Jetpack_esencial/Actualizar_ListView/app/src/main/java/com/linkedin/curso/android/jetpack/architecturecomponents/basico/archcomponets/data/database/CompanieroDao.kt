package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface CompanieroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(companiero: CompanieroEntity)

    @Query("DELETE FROM companiero")
    fun deleteAll()

    @Query("Select * from companiero")
    fun selectAllCompanieros(): List<CompanieroEntity>
}