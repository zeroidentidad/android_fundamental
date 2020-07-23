package com.linkedin.curso.android.mitiempo.mitiempo.utils

import android.widget.ImageView
import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.squareup.picasso.Picasso

class ImagenesService {
    // Información en http://square.github.io/picasso/

    fun getImageOpenW(contenedor: ImageView, icon_path: String) {
        Picasso.get()
                .load(BuildConfig.weather_icon_url + icon_path)
                .error(R.drawable.ic_error)
                .centerInside()
                .into(contenedor)
    }

}