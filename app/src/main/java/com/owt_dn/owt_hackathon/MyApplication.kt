package com.owt_dn.owt_hackathon

import android.app.Application
import com.cloudinary.android.MediaManager
import com.owt_dn.owt_hackathon.environments.Environment

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MediaManager.init(this, mapOf("cloud_name" to Environment.Cloudinary.CLOUD_NAME))
    }
}