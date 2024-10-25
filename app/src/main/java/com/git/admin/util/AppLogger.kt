package com.git.admin.util

import android.util.Log
import com.git.admin.BuildConfig
import java.util.Locale

/** Created by Tony on 12/1/2024. */

object AppLogger {
    private val localeID = Locale("in", "ID")

    fun logE(message: String) {
        if (BuildConfig.DEBUG) Log.e("ERROR_OFFERIGHT", message)
    }
    fun logD(message: String) {
        if (BuildConfig.DEBUG) Log.d("DEBUG_OFFERIGHT", message)
    }
}