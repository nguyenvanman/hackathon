package com.owt_dn.owt_hackathon.utils

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.provider.MediaStore.Images.Thumbnails.WIDTH
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import java.io.ByteArrayOutputStream


fun Bitmap.toByteArray(): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}

@Throws(WriterException::class)
fun encodeAsBitmap(str: String): Bitmap? {
    val result: BitMatrix = try {
        MultiFormatWriter().encode(
            str,
            BarcodeFormat.QR_CODE, 300, 300, null
        )
    } catch (iae: IllegalArgumentException) {
        return null
    }
    val w = result.width
    val h = result.height
    val pixels = IntArray(w * h)
    for (y in 0 until h) {
        val offset = y * w
        for (x in 0 until w) {
            pixels[offset + x] = if (result[x, y]) BLACK else WHITE
        }
    }
    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    bitmap.setPixels(pixels, 0, 300, 0, 0, w, h)
    return bitmap
}