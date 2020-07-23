package com.linkedin.curso.android.mitiempo.mitiempo.repositorio.bbdd.entidades

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "usuarios")
data class Usuario(
        @PrimaryKey(autoGenerate = false) @NotNull @ColumnInfo(name = "id_usr") val id: String,
        @NotNull val email: String)