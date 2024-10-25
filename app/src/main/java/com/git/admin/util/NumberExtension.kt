package com.git.admin.util

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Double.toUSD(): String {
    return "$${String.format("%,.0f", this)}"
}

fun Long.toUSD(): String {
    return this.toDouble().toUSD()
}

fun Int.toUSD(): String {
    return this.toDouble().toUSD()
}