package com.linkedin.curso.android.jetpack.architecturecomponents.basico.lifecycle

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import kotlinx.android.synthetic.main.nombre_fragment.*


class NombreFragment : Fragment() {

    companion object {
        fun newInstance() = NombreFragment()
    }

    private lateinit var viewModel: NombreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.nombre_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NombreViewModel::class.java)
        subscribeChanges()
    }

    private fun subscribeChanges() {
        nombreET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.nombreLiveData.postValue(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })


        viewModel.nombreLiveData.observe(this.activity!!, Observer<String> { value ->
            if (value != null) {
                nombreTV.text = value
            }
        })
    }

}
