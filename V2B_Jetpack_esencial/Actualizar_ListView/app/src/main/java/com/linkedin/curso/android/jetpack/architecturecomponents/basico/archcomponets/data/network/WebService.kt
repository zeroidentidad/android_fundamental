package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.network

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.CompanieroEntity


class WebService(val context: Context) {

    var mCompaniero: ArrayList<CompanieroEntity>

    init {
        val equipoFile = context.assets.open("equipo.json")
        val size = equipoFile.available()
        val buffer = ByteArray(size)
        equipoFile.read(buffer)
        equipoFile.close()
        val jsonString = String(buffer)

        val gson = Gson()
        val collectionType = object : TypeToken<List<CompanieroEntity>>() {}.type
        mCompaniero = gson.fromJson(jsonString, collectionType)
    }

    companion object {
        private var mInstance: WebService? = null

        @Synchronized
        fun getInstance(context: Context): WebService {
            if (mInstance == null) {
                mInstance = WebService(context)
            }
            return this.mInstance!!
        }
    }


    fun getCompanieros(): MutableLiveData<List<CompanieroEntity>> {
        val liveData = MutableLiveData<List<CompanieroEntity>>()
        liveData.postValue(mCompaniero)
        return liveData
    }
}