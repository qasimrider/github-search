package com.dawn.common.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun String.uiDateFormatter(): String {
    val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    return SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.ENGLISH).format(formatter.parse(this))
}

fun String.toDateServerFormat(): Date {
    val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    return formatter.parse(this)!!
}


fun Date.serverDateFormatter(): String {
    val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    return formatter.format(this)
}

fun Date.monthYearFormat(): String {
    val formatter: DateFormat = SimpleDateFormat("MMM/yyyy", Locale.ENGLISH)
    return formatter.format(this)
}

fun Date.minutesHoursFormat(): String {
    val formatter: DateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return formatter.format(this)
}