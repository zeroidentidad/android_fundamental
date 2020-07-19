package com.linkedin.curso.android.jetpack.architecturecomponents.basico.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log


class ConometroLifecycleObserver : LifecycleObserver {

    private val TAG_LOG = "LifecycleObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun suenaAlert() {
        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 300)
        Log.d(TAG_LOG, "suenaAlert")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun suenaAnswer() {
        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen1.startTone(ToneGenerator.TONE_CDMA_ANSWER, 300)
        Log.d(TAG_LOG, "suenaAnswer")
    }
}