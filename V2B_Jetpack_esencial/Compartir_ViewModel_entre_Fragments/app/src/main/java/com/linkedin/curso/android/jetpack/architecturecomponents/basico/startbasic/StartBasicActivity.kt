package com.linkedin.curso.android.jetpack.architecturecomponents.basico.startbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkedin.curso.android.jetpack.architecturecomponents.R

class StartBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_basic_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, StartBasicFragment.newInstance())
                    .commitNow()
        }
    }

}
