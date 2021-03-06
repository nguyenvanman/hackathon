package com.owt_dn.owt_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.adapters.ProfilePagerAdapter
import com.owt_dn.owt_hackathon.fragments.ProfileInfoFragment
import com.owt_dn.owt_hackathon.fragments.ProfilePasswordFragment
import com.owt_dn.owt_hackathon.services.apis.implementations.ProfileService
import com.owt_dn.owt_hackathon.services.apis.models.ProfileForm
import com.owt_dn.owt_hackathon.services.apis.models.RegistrationResponse
import com.owt_dn.owt_hackathon.utils.PreferencesUtils
import com.owt_dn.owt_hackathon.views.LoadingDialog
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.toast
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private var isValid = false

    lateinit var loadingDialog: LoadingDialog

    private var profileForm = ProfileForm()

    private val profileInfoFragment = ProfileInfoFragment().apply {
        onValidate = { isValid, profileForm ->
            this@RegistrationActivity.isValid = isValid
            this@RegistrationActivity.profileForm.copyInfo(profileForm)
        }
    }

    private val profilePasswordFragment = ProfilePasswordFragment().apply {
        onBack = {
            this@RegistrationActivity.viewPager.previousPage()
        }

        onPasswordInput = {
            this@RegistrationActivity.profileForm.password = it
        }
    }

    private val fragments = listOf(
        profileInfoFragment,
        profilePasswordFragment
    )

    private var isOnLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initialize()
    }

    private fun initialize() {
        loadingDialog = LoadingDialog(this)

        viewPager.adapter = ProfilePagerAdapter(supportFragmentManager, fragments)

        viewPager.onPageChanged { position ->
            isOnLastPage = position == fragments.size - 1
            setButtonState()
        }

        btnNext.setOnClickListener {
            if (isOnLastPage) {
                if (profileForm.password != null) {
                    submit()
                } else {
                    toast(getString(R.string.text_missing_password))
                }
            } else {
                if (isValid) {
                    viewPager.nextPage()
                } else {
                    toast(getString(R.string.text_missing_fields))
                }
            }
        }
    }

    private fun setButtonState() {
        btnNext.isEnabled = isValid
        btnNext.text = getString(if (isOnLastPage) R.string.button_text_submit else R.string.button_text_next)
        btnNext.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (isOnLastPage) R.drawable.ic_submit else R.drawable.ic_next, 0)
    }

    private fun submit() {
        loadingDialog.show()
        ProfileService.register(
            profileForm = profileForm,
            onSuccess = ::onSubmitSuccess,
            onError = ::onSubmitFailed
        )
    }

    private fun onSubmitSuccess(registrationResponse: RegistrationResponse) {
        loadingDialog.hide()
        PreferencesUtils.saveToken(this, registrationResponse.token)
        startActivity(Intent(this, QrCodeActivity::class.java).also {
            it.putExtra(QrCodeActivity.QR_CODE_KEY, registrationResponse.qrCode)
        })
        finish()
    }

    private fun onSubmitFailed(error: String) {
        loadingDialog.hide()
        toast(error)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sign_in_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionSignIn) {
            startActivity(Intent(this, SignInActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}