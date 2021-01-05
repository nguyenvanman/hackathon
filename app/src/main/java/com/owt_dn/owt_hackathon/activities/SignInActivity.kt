package com.owt_dn.owt_hackathon.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.services.apis.implementations.ProfileService
import com.owt_dn.owt_hackathon.services.apis.models.RegistrationResponse
import com.owt_dn.owt_hackathon.services.apis.models.SignInForm
import com.owt_dn.owt_hackathon.utils.PreferencesUtils
import com.owt_dn.owt_hackathon.views.LoadingDialog
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.toast

class SignInActivity : AppCompatActivity() {
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initialize()
    }

    private fun initialize() {
        loadingDialog = LoadingDialog(this)

        btnSignIn.setOnClickListener {
            if (edtUsername.text().isBlank() || edtPassword.text().isBlank()) {
                toast(R.string.text_missing_fields)
            } else {
                doSignIn()
            }
        }
    }

    private fun doSignIn() {
        loadingDialog.show()
        ProfileService.signIn(
            signInForm = SignInForm(username = edtUsername.text(), password = edtPassword.text()),
            onSuccess = ::onSignInSuccess,
            onError = ::onError
        )
    }

    private fun onSignInSuccess(registrationResponse: RegistrationResponse) {
        loadingDialog.hide()
        PreferencesUtils.saveToken(this, registrationResponse.token)
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    private fun onError(error: String) {
        loadingDialog.hide()
        toast(error)
    }
}