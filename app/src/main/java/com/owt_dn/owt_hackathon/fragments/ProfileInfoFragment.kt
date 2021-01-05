package com.owt_dn.owt_hackathon.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.services.CloudinaryService
import com.owt_dn.owt_hackathon.utils.toByteArray
import kotlinx.android.synthetic.main.fragment_profile_info.*

class ProfileInfoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        imgAvatar.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivityForResult(intent, 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap = data?.extras?.get("data") as Bitmap
        imgAvatar.setImageBitmap(bitmap)
        CloudinaryService.upload(
            bitmap.toByteArray(),
        onSuccess = {

        },
        onError = {

        })
    }
}