package com.owt_dn.owt_hackathon.views

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import com.owt_dn.owt_hackathon.R

class LoadingDialog(activity: Activity) : Dialog(activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        setCancelable(false)
    }
}