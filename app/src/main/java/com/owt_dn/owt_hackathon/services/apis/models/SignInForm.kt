package com.owt_dn.owt_hackathon.services.apis.models

import com.google.gson.annotations.SerializedName

data class SignInForm(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)