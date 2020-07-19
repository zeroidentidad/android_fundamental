package com.linkedin.curso.android.jetpack.architecturecomponents.basico.startbasic

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.linkedin.curso.android.jetpack.architecturecomponents.R

class StartBasicFragment : Fragment() {

    companion object {
        fun newInstance() = StartBasicFragment()
    }

    private lateinit var viewModel: StartBasicViewModel
    private lateinit var message: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.start_basic_fragment, container, false)
        message = view.findViewById(R.id.message)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StartBasicViewModel::class.java)
        message.text = viewModel.data.value

    }

}
