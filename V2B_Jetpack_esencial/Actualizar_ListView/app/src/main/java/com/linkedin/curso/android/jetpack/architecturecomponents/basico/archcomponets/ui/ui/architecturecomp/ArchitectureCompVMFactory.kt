package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.ui.architecturecomp

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.ArchCompRepository

class MainViewModelFactory(private val mRepository: ArchCompRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return ArchitectureCompViewModel(mRepository) as T
    }
}