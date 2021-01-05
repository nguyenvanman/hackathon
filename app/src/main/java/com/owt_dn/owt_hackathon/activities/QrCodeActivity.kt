package com.owt_dn.owt_hackathon.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.owt_dn.owt_hackathon.R
import com.owt_dn.owt_hackathon.environments.Environment.WEB_CHECK_QR_CODE_PATH
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
        val qrCodeGuid = intent.getStringExtra(QR_CODE_KEY) ?: ""
        val qrCodeContent = "${WEB_CHECK_QR_CODE_PATH}$qrCodeGuid"
        val bitmap = encodeAsBitmap(qrCodeContent)
        imgQrCode.setImageBitmap(bitmap)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionHome -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}