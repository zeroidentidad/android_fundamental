package com.linkedin.curso.android.jetpack.architecturecomponents.basico.ui.startbasic

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class StartBasicViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data


    init {
        _data.value = "¡Hola, Jetpack!"
    }
}
