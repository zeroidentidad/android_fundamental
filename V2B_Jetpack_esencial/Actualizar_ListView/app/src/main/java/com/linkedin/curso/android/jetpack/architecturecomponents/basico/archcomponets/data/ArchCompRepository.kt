package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data

import android.arch.lifecycle.MutableLiveData
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.AppExecutor
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.CompanieroDao
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.CompanieroEntity
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.network.WebService

class ArchCompRepository(var executor: AppExecutor, var companieroDao: CompanieroDao, webService: WebService) {
    val companierosObs = MutableLiveData<List<CompanieroEntity>>()

    init {
        val networkData = webService.getCompanieros()
        networkData.observeForever { newDataFromServer ->
            executor.hiloDisco().execute {
                companieroDao.deleteAll()
                newDataFromServer?.forEach {
                    companieroDao.insert(it)
                }
                getInfoBaseDatos()
            }
        }
    }

    private fun getInfoBaseDatos() {
        companierosObs.postValue(companieroDao.selectAllCompanieros())
    }

    companion object {
        private var mInstance: ArchCompRepository? = null
        @Synchronized
        fun getInstance(companieroDao: CompanieroDao, executor: AppExecutor, webService: WebService): ArchCompRepository {
            if (mInstance == null) {
                mInstance = ArchCompRepository(executor, companieroDao, webService)
            }
            return mInstance as ArchCompRepository
        }
    }
}