package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.ui.architecturecomp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.ArchCompRepository
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.CompanieroEntity

class ArchitectureCompViewModel(var mRepository: ArchCompRepository) : ViewModel() {
    var mListaCompanieros = MutableLiveData<List<CompanieroEntity>>()

    init {
        mRepository.companierosObs.observeForever { companierosCambio ->
            mListaCompanieros.postValue(companierosCambio)
        }
    }
}
