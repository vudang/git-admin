package com.git.admin.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date


object DateFormat {
    // Basic Date Formats
    val basicDateFormat1 = "dd/MM/yyyy"
    val basicDateFormat2 = "MM/dd/yyyy"
    val basicDateFormat3 = "yyyy/MM/dd"
    val basicDateFormat4 = "dd-MM-yyyy"
    val basicDateFormat5 = "MM-dd-yyyy"
    val basicDateFormat6 = "yyyy-MM-dd"
    val basicDateFormat7 = "dd.MM.yyyy"

    // Date with Day Name
    val dayNameFormat1 = "EEE, dd/MM/yyyy"
    val dayNameFormat2 = "EEEE, dd MMMM yyyy"
    val dayNameFormat3 = "EEE, MMM d, ''yy"

    // Date with Time (24-hour format)
    val time24HourFormat1 = "yyyy-MM-dd HH:mm:ss"
    val time24HourFormat2 = "dd/MM/yyyy HH:mm"
    val time24HourFormat3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    // Date with Time (12-hour format)
    val time12HourFormat1 = "yyyy-MM-dd hh:mm:ss a"
    val time12HourFormat2 = "dd/MM/yyyy hh:mm a"

    // Date with Time and Time Zone
    val timeZoneFormat1 = "yyyy-MM-dd HH:mm:ss z"
    val timeZoneFormat2 = "yyyy-MM-dd HH:mm:ss Z"
    val timeZoneFormat3 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"

    // ISO 8601 Formats
    val iso8601Format1 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val iso8601Format2 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"

    // Custom Date Formats
    val customFormat1 = "EEEE, MMMM d, yyyy"
    val customFormat2 = "EEE, d MMM yyyy HH:mm:ss Z"
    val customFormat3 = "MMM yyyy"
    val customFormat4 = "MMMM yyyy"
    val customFormat5 = "yyyy.MM.dd G 'at' HH:mm:ss z"
    val customFormat6 = "w 'week of' yyyy"
    val customFormat7 = "D 'day of' yyyy"
}

@SuppressLint("SimpleDateFormat")
fun Date.toStringWithFormat(format: String = DateFormat.basicDateFormat1): String {
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(this)
}