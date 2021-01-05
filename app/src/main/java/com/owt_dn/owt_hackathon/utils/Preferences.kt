package com.owt_dn.owt_hackathon.utils

import android.content.Context
import com.owt_dn.owt_hackathon.MyApplication

object Preferences {
    private const val PREF_NAME = "owt_hackathon_pref"
    private const val TOKEN_KEY = "token"

    fun saveToken(context: Context, token: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().apply {
            putString(TOKEN_KEY, token)
            apply()
        }
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(TOKEN_KEY, "")
    }
}