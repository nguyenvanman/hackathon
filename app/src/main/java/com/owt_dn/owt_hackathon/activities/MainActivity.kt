package com.owt_dn.owt_hackathon.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.owt_dn.owt_hackathon.utils.PreferencesUtils

class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()

        checkTokenAndNavigate()
    }

    private fun checkTokenAndNavigate() {
        if (PreferencesUtils.getToken(this).isNullOrBlank()) {
            startActivity(Intent(this, RegistrationActivity::class.java))
        } else {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        finish()
    }
}