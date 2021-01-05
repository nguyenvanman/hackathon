package com.owt_dn.owt_hackathon.services.apis.models

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("token") val token: String,
    @SerializedName("q_r_code") val qrCode: String
)