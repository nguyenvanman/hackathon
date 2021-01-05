package com.owt_dn.owt_hackathon.services

import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.owt_dn.owt_hackathon.environments.Environment

object CloudinaryService {
    fun upload(byteArray: ByteArray, onSuccess: (String) -> Unit, onProgress: ((Long, Long) -> Unit) ?= null, onError: ((String?) -> Unit) ?= null) {
        MediaManager.get().registerCallback(object: UploadCallback {
            override fun onStart(requestId: String?) {
                Log.d("CLOUDINARY_UPLOAD", "STARTED")
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                onProgress?.invoke(bytes, totalBytes)
            }

            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                Log.d("CLOUDINARY_UPLOAD", "SUCCESS")
                onSuccess.invoke(resultData?.get("url").toString())
            }

            override fun onError(requestId: String?, error: ErrorInfo?) {
                Log.d("CLOUDINARY_UPLOAD", "FAILED")
                onError?.invoke(error?.description)
            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                Log.d("CLOUDINARY_UPLOAD", "RESCHEDULED")
            }

        })
        MediaManager.get().upload(byteArray).unsigned(Environment.Cloudinary.UPLOAD_UNSIGNED_PRESET).dispatch()
    }
}