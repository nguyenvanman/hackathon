package com.owt_dn.owt_hackathon.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.environments.Environment
import com.owt_dn.owt_hackathon.services.apis.implementations.ProfileService
import com.owt_dn.owt_hackathon.services.apis.models.ProfileResponse
import com.owt_dn.owt_hackathon.utils.PreferencesUtils
import com.owt_dn.owt_hackathon.utils.encodeAsBitmap
import com.owt_dn.owt_hackathon.views.LoadingDialog
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

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
        tvPhoneNumber.text = PhoneNumberUtils.formatNumber(profileResponse.phone, Locale.getDefault().country)
        tvPersonalID.text = profileResponse.personalId
        showQrCode(profileResponse.qrCode)

        if (profileResponse.firstVaccinateDate == null) {
            blockVaccinationCalendar.visibility =  View.GONE
        } else {
            blockVaccinationCalendar.visibility =  View.VISIBLE
            tvFirstTime.text = SimpleDateFormat("dd MM yyyy").format(profileResponse.firstVaccinateDate)
            tvSecondTime.text = SimpleDateFormat("dd MM yyyy").format(profileResponse.secondVaccinateDate!!)
        }

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

    private fun showQrCode(qrCodeGuid: String) {
        val qrCodeContent = "${Environment.WEB_CHECK_QR_CODE_PATH}$qrCodeGuid"
        val bitmap = encodeAsBitmap(qrCodeContent)
        imgQrCode.setImageBitmap(bitmap)
    }

    override fun onBackPressed() {
    }
}