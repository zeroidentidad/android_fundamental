package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.entidades.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = REPLACE)
    fun insert(usuario: Usuario)

    @Delete
    fun deleteUser(usuario: Usuario)

    @Query("DELETE FROM usuarios")
    fun deleteAll()

    @Query("Select * from usuarios")
    fun selectAll(): LiveData<List<Usuario>>

    @Query("Select email from usuarios where id_usr == :id")
    fun getEmail(id: String): LiveData<String>
}