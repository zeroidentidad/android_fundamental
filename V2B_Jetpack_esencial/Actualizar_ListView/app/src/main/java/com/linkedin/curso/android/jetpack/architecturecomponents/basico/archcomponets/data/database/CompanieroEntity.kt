package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

//      Tabla compañero
//      |   id  |   nombre  |   compania    |   email   |   telf    |

@Entity(tableName = "companiero")
data class CompanieroEntity(
        @PrimaryKey(autoGenerate = false) @NotNull val id: String,
        @NotNull val nombre: String,
        @NotNull val compania: String,
        @NotNull val email: String,
        @NotNull val telf: String)


