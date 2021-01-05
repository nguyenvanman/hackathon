package com.owt_dn.owt_hackathon.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.services.apis.implementations.ProfileService
import com.owt_dn.owt_hackathon.services.apis.models.ProfileResponse
import com.owt_dn.owt_hackathon.utils.PreferencesUtils
import com.owt_dn.owt_hackathon.views.LoadingDialog
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class ProfileActivity : AppCompatActivity() {
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initialize()

        getProfile()
    }

    private fun initialize() {
        loadingDialog = LoadingDialog(this)
    }

    private fun getProfile() {
        loadingDialog.show()
        val token = "Bearer ${PreferencesUtils.getToken(this)}"
        ProfileService.getProfile(
            token = token,
            onSuccess = ::onGetProfileSuccess,
            onError = ::onGetProfileFailed
        )
    }

    private fun onGetProfileSuccess(profileResponse: ProfileResponse) {
        loadingDialog.hide()
        showProfile(profileResponse)
    }

    private fun onGetProfileFailed(error: String) {
        loadingDialog.hide()
        toast(error)
    }

    @SuppressLint("SimpleDateFormat")
    private fun showProfile(profileResponse: ProfileResponse) {
        Glide.with(this).load(profileResponse.profileUrl).into(imgAvatar)
        tvFullName.text = profileResponse.fullName
        tvGender.text = profileResponse.gender
        tvBirthday.text = SimpleDateFormat("dd MM yyyy").format(profileResponse.birthday)
        tvAddress.text = profileResponse.address
        tvEmail.text = profileResponse.email
        tvPhoneNumber.text = profileResponse.phone
        tvPersonalID.text = profileResponse.personalId
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionSignIn -> {
                startActivity(Intent(this, SignInActivity::class.java))
                PreferencesUtils.clearToken(this)
                finish()
                true
            }
            R.id.actionSignUp -> {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
    }
}