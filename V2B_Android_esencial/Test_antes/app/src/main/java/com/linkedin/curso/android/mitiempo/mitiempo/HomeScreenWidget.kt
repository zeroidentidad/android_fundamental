package com.linkedin.curso.android.mitiempo.mitiempo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ModelosProfile
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ProfilesServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of App Widget functionality.
 */
class HomeScreenWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        private var disposable: Disposable? = null
        private val profileServicio by lazy {
            ProfilesServicio.crear()
        }

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            getInfoProfile(context, appWidgetManager, appWidgetId)
        }

        private fun getInfoProfile(context: Context, appWidgetManager: AppWidgetManager,
                                   appWidgetId: Int) {
            val pais = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales.get(0).country
            } else {
                context.resources.configuration.locale.country
            }

            // Llamamos a un servicio de ejemplo para coger datos de profiles
            disposable = profileServicio.getProfile(pais)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { profile -> actualizarWidget(profile, context, appWidgetManager, appWidgetId) },
                            { error -> L.d("Error ${error.message}") })
        }

        private fun actualizarWidget(profile: ModelosProfile.Resultado?, context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val widgetText = context.getString(R.string.appwidget_text)
            val views = RemoteViews(context.packageName, R.layout.home_screen_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText + " ${profile?.results?.get(0)?.name?.first}")
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}



