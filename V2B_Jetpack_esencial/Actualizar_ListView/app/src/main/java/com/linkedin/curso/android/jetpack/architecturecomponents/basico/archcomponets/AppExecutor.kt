package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutor {
    private var mDisco: Executor? = null
    private var mHiloPrincipal: Executor? = null
    private var mNetwork: Executor? = null

    constructor (disco: Executor, network: Executor, hiloPrincipal: Executor) {
        this.mDisco = disco
        this.mNetwork = network
        this.mHiloPrincipal = hiloPrincipal
    }

    companion object {
        private var mInstancia: AppExecutor? = null

        @Synchronized
        fun getInstance(): AppExecutor {
            if (mInstancia == null) {
                mInstancia = AppExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        MainThreadExecutor())
            }
            return this.mInstancia!!
        }
    }

    fun hiloDisco(): Executor {
        return this.mDisco!!
    }

    fun hiloPrincipal(): Executor {
        return this.mHiloPrincipal!!
    }

    fun hiloNetwork(): Executor {
        return this.mNetwork!!
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}


