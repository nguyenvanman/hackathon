package com.owt_dn.owt_hackathon.services.apis.interfaces

import com.owt_dn.owt_hackathon.services.apis.models.ProfileForm
import com.owt_dn.owt_hackathon.services.apis.models.RegistrationResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IProfileService {
    @POST("patients/profile")
    fun register(@Body profileForm: ProfileForm) : Observable<Response<RegistrationResponse>>
}