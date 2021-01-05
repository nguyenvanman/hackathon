package com.owt_dn.owt_hackathon.services.apis.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProfileForm(
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("personal_id") var personalId: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("profile_url") var profileUrl: String? = null,
    @SerializedName("sex") var gender: String? = null,
    @SerializedName("birthday") var birthday: Date? = null,
    @SerializedName("address") var address: String? = null
) {
    fun copyInfo(profileForm: ProfileForm) {
        fullName = profileForm.fullName
        personalId = profileForm.personalId
        email = profileForm.email
        phone = profileForm.phone
        profileUrl = profileForm.profileUrl
        gender = profileForm.gender
        birthday = profileForm.birthday
        address = profileForm.address
    }
}