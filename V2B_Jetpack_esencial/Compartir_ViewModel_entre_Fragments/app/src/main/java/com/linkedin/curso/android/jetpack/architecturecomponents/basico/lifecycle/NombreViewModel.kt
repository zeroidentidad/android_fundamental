package com.linkedin.curso.android.jetpack.architecturecomponents.basico.lifecycle

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;

class NombreViewModel : ViewModel() {
    var nombreLiveData = MutableLiveData<String>()
}
