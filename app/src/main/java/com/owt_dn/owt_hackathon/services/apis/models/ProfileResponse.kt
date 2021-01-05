package com.owt_dn.owt_hackathon.services.apis.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProfileResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("personal_id") val personalId: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("password") val password: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("q_r_code") val qrCode: String,
    @SerializedName("sex") val gender: String,
    @SerializedName("birthday") val birthday: Date,
    @SerializedName("address") val address: String,
    @SerializedName("created_date") val createdDate: Date,
    @SerializedName("approval_date") val approvalDate: Date,
    @SerializedName("valid") val isValid: Boolean,
    @SerializedName("remind_first_vaccinate_date_time") val remindFirstVaccinateDate: Date,
    @SerializedName("first_vaccinate_date_time") val firstVaccinateDate: Date? = null,
    @SerializedName("first_vaccinate_description") val firstVaccinateDescription: Date? = null,
    @SerializedName("remind_second_vaccinate_date_time") val remindSecondVaccinateDate: Date? = null,
    @SerializedName("second_vaccinate_date_time") val secondVaccinateDate: Date? = null,
    @SerializedName("second_vaccinate_description") val secondVaccinateDescription: Date? = null,
    @SerializedName("status") val status: String
)