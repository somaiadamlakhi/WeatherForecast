package com.startappz.weatherforcast.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(timestamp: Int?): String {
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.ENGLISH)
    timestamp?.let {
        val date = java.util.Date(timestamp.toLong() * 1000)

        return sdf.format(date)
    }
    return sdf.format(0)

}

fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.ENGLISH)
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun Double.formatDecimals(): String {
    return " %.0f".format(this)
}

