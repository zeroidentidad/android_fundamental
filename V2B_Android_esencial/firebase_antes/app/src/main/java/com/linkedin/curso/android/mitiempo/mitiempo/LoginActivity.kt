package com.linkedin.curso.android.mitiempo.mitiempo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L


class LoginActivity : BaseActivity(), View.OnClickListener {
    private val mCodigoSingIn = 9001
    private var mAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mProgressBar: ProgressBar? = null
    private var mStatusTextView: TextView? = null
    private var mDetailTextView: TextView? = null
    private var mButtonSingIn: SignInButton? = null
    private var mButtonSingOut: Button? = null
    private var mButtonDisconnect: Button? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            mButtonSingIn?.id -> {
                signIn()
            }
            mButtonSingOut?.id -> {
                signOut()
            }
            mButtonDisconnect?.id -> {
                revokeAccess()
            }
            else -> {
                L.v("Identificador no definido")
            }
        }
    }

    override fun initViews() {
        mProgressBar = findViewById(R.id.activity_login_progress_bar)
        mStatusTextView = findViewById(R.id.activity_login_status)
        mDetailTextView = findViewById(R.id.activity_login_detail)
        mButtonSingIn = findViewById(R.id.activity_login_sign_in_button)
        mButtonSingOut = findViewById(R.id.activity_login_sign_out_button)
        mButtonDisconnect = findViewById(R.id.activity_login_disconnect_button)

        // OnClick listener
        mButtonSingIn?.setOnClickListener(this)
        mButtonSingOut?.setOnClickListener(this)
        mButtonDisconnect?.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO: Conectar con google y firebase

        initViews()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.currentUser
        updateUI(currentUser)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Resultado que devuelve Google en GoogleSignInApi.getSignInIntent(...);
        if (requestCode == mCodigoSingIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // La autentificación con Google ha sido satisfactoria, ahora entra Firebase
                // TODO Entrar con Firebase
            } catch (e: ApiException) {
                // La autentificación con Google falla y habrá que actualizar la UI apropiadamente
                L.w("La autentificación con Google ha fallado", e)
                updateUI(null)
            }

        }
    }

    private fun updateUI(user: FirebaseUser?) {
        mProgressBar?.visibility = View.GONE
        if (user != null) {
            mStatusTextView?.text = getString(R.string.google_status_fmt, user.email)
            mDetailTextView?.text = getString(R.string.firebase_status_fmt, user.uid)
            mButtonSingIn?.visibility = View.GONE
            mButtonDisconnect?.visibility = View.VISIBLE
        } else {
            mStatusTextView?.setText(R.string.signed_out)
            mDetailTextView?.text = null
            mButtonSingIn?.visibility = View.VISIBLE
            mButtonDisconnect?.visibility = View.GONE
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        L.d("firebaseAuthWithGoogle:" + acct.id!!)
        mProgressBar?.visibility = View.VISIBLE

        // TODO: Generar las credenciales con GoogleAuthProvider

    }

    private fun signIn() {
        startActivityForResult(mGoogleSignInClient?.signInIntent, mCodigoSingIn)
    }

    private fun signOut() {
        // Sing out de Firebase
        mAuth?.signOut()

        // Sing out en Google
    }

    private fun revokeAccess() {
        // Sing out de Firebase
        mAuth?.signOut()

        // TODO: Revocar el acceso en Google
    }
}
