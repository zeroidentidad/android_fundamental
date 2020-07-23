package com.linkedin.curso.android.mitiempo.mitiempo.utils

import android.content.Context
import android.widget.ImageView
import com.linkedin.curso.android.mitiempo.mitiempo.BuildConfig
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation


class ImagenesService {
    // Información en http://square.github.io/picasso/
    fun getImageOpenW(contenedor: ImageView, icon_path: String) {
        Picasso.get()
                .load(BuildConfig.weather_icon_url + icon_path + ".png")
                .error(R.drawable.ic_error)
                .into(contenedor)
    }

    fun getImagen(contenedor: ImageView, url: String) {
        Picasso.get()
                .load(url)
                .error(R.drawable.ic_error)
                .into(contenedor)
    }

    fun getBlurImage(context: Context, contenedor: ImageView, url: String) {
        Picasso.get().load(url)
                .error(R.drawable.ic_error)
                .transform(BlurTransformation(context))
                .fit()
                .centerCrop()
                .into(contenedor)
    }

}