package com.owt_dn.owt_hackathon.environments

object Environment {
    object Cloudinary {
        const val CLOUD_NAME = "dw0yzvsvn"
        const val UPLOAD_UNSIGNED_PRESET = "hubvo3vl"
    }

    object Api {
        const val BASE_URL = "http://27.71.235.200:8888/api/"
    }

    const val WEB_CHECK_QR_CODE_PATH = "http://localhost:3333/check?code="
}