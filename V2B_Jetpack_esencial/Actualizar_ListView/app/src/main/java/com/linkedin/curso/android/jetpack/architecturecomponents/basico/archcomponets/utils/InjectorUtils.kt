package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.utils

import android.content.Context
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.AppExecutor
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.ArchCompRepository
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.AplicacionRoomDatabase
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.network.WebService
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.ui.architecturecomp.MainViewModelFactory

class InjectorUtils {
    companion object {
        fun inyectarRepositorio(context: Context): ArchCompRepository {
            val database = AplicacionRoomDatabase.getInstance(context.applicationContext)
            val executor = AppExecutor.getInstance()
            val webService = WebService.getInstance(context)
            return ArchCompRepository.getInstance(database?.companieroDao()!!, executor, webService)
        }

        fun inyectarArchComViewModelFactory(context: Context): MainViewModelFactory {
            val repository = inyectarRepositorio(context.applicationContext)
            return MainViewModelFactory(repository)
        }
    }
}