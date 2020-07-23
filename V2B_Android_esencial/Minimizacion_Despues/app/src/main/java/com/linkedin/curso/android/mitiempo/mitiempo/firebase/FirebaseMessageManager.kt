package com.linkedin.curso.android.mitiempo.mitiempo.firebase

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L


class FirebaseMessageManager : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(refreshedToken)

        L.e("Refresh token: $refreshedToken")

    }
}