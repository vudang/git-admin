package com.git.admin.util

import android.content.Context
import android.widget.Toast

/** Created by Tony on 5/12/2023. */

object Extensions {
    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}