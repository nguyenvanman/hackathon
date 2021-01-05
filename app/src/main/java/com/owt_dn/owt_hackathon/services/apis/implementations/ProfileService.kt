package com.owt_dn.owt_hackathon.services.apis.implementations

import android.annotation.SuppressLint
import com.owt_dn.owt_hackathon.services.apis.ApiClient
import com.owt_dn.owt_hackathon.services.apis.interfaces.IProfileService
import com.owt_dn.owt_hackathon.services.apis.models.ProfileForm
import com.owt_dn.owt_hackathon.services.apis.models.ProfileResponse
import com.owt_dn.owt_hackathon.services.apis.models.RegistrationResponse
import com.owt_dn.owt_hackathon.services.apis.models.SignInForm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ProfileService {
    @SuppressLint("CheckResult")
    fun register(profileForm: ProfileForm, onSuccess: (RegistrationResponse) -> Unit, onError: (String) -> Unit) {
        ApiClient.createService(IProfileService::class.java)!!
            .register(profileForm)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body()!!)
                } else {
                    onError.invoke(response.errorBody()?.string().toString())
                }
            }, { exception ->
                onError.invoke(exception.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun getProfile(token: String, onSuccess: (ProfileResponse) -> Unit, onError: (String) -> Unit) {
        ApiClient.createService(IProfileService::class.java)!!
            .getProfile(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body()!!)
                } else {
                    onError.invoke(response.errorBody()?.string().toString())
                }
            }, { exception ->
                onError.invoke(exception.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun signIn(signInForm: SignInForm, onSuccess: (RegistrationResponse) -> Unit, onError: (String) -> Unit) {
        ApiClient.createService(IProfileService::class.java)!!
            .signIn(signInForm)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body()!!)
                } else {
                    onError.invoke(response.errorBody()?.string().toString())
                }
            }, { exception ->
                onError.invoke(exception.message.toString())
            })
    }
}