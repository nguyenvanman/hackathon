package com.owt_dn.owt_hackathon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.utils.encodeAsBitmap
import kotlinx.android.synthetic.main.activity_qr_code.*

class QrCodeActivity : AppCompatActivity() {
    companion object {
        const val QR_CODE_KEY = "q_r_code"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        showQrCode()
    }

    private fun showQrCode() {
        val qrCode = intent.getStringExtra(QR_CODE_KEY) ?: ""
        val bitmap = encodeAsBitmap(qrCode)
        imgQrCode.setImageBitmap(bitmap)
    }
}