package com.owt_dn.owt_hackathon.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.services.CloudinaryService
import com.owt_dn.owt_hackathon.services.apis.models.Gender
import com.owt_dn.owt_hackathon.services.apis.models.ProfileForm
import com.owt_dn.owt_hackathon.utils.showDatePicker
import com.owt_dn.owt_hackathon.utils.toByteArray
import kotlinx.android.synthetic.main.fragment_profile_info.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.support.v4.toast
import java.text.SimpleDateFormat
import java.util.*

class ProfileInfoFragment : Fragment() {

    private var birthday: Date? = null
    private var fullName: String? = null
    private var profileUrl: String? = null
    private var gender: String? = null
    private var personalId: String? = null
    private var email: String? = null
    private var phoneNumber: String? = null
    private var address: String? = null

    var onValidate: ((Boolean, ProfileForm) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initialize() {
        imgAvatar.setOnClickListener {
            openCamera()
        }

        btnChooseBirthday.setOnClickListener {
            context!!.showDatePicker(onDateSet = {
                birthday = it
                tvBirthday.text = SimpleDateFormat("dd MM yyyy").format(birthday!!)
                validate()
            })
        }

        tvFullName.onTextChanged {
            fullName = it
            validate()
        }

        tvAddress.onTextChanged {
            address = it
            validate()
        }

        tvEmail.onTextChanged {
            email = it
            validate()
        }

        tvPersonalID.onTextChanged {
            personalId = it
            validate()
        }

        tvPhoneNumber.onTextChanged {
            phoneNumber = it
            validate()
        }

        radioGroupGender.onCheckedChange { _, checkedId ->
            gender = mapOf(
                R.id.male to Gender.MALE,
                R.id.female to Gender.FEMALE,
                R.id.other to Gender.OTHER
            )[checkedId]
            validate()
        }
    }

    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivityForResult(intent, 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val bitmap = data?.extras?.get("data") as Bitmap
            imgAvatar.setImageResource(R.drawable.loading_progress)
            CloudinaryService.upload(
                bitmap.toByteArray(),
                onSuccess = {
                    profileUrl = it
                    Glide.with(this).load(it).into(imgAvatar)
                    validate()
                },
                onError = { toast(it.toString()) })
        }
    }

    private fun validate() {
        val isValid =   !fullName.isNullOrBlank() &&
                        !personalId.isNullOrBlank() &&
                        !email.isNullOrBlank() &&
                        !phoneNumber.isNullOrBlank() &&
                        !gender.isNullOrBlank() &&
                        birthday != null &&
                        !address.isNullOrBlank() &&
                        !profileUrl.isNullOrBlank()
        onValidate?.invoke(
            isValid,
            ProfileForm(
                fullName = fullName,
                personalId = personalId,
                email = email,
                phone = phoneNumber,
                profileUrl = profileUrl,
                gender = gender,
                birthday = birthday,
                address = address
            )
        )
    }
}