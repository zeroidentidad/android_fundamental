package com.linkedin.curso.android.jetpack.architecturecomponents.basico

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.ui.startbasic.StartBasicFragment

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
