package com.owt_dn.owt_hackathon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.utils.encodeAsBitmap
import kotlinx.android.synthetic.main.activity_qr_code.*

class QrCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        test()
    }


    private fun test() {
        val qrCode = intent.getStringExtra("q_r_code") ?: ""
        val bitmap = encodeAsBitmap(qrCode)
        imgQrCode.setImageBitmap(bitmap)
    }
}