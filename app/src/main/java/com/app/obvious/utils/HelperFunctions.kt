package com.app.obvious.utils

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat

object HelperFunctions {
    //Changing date format from  yyyy-MM-dd to dd-MM-yyyy
    @SuppressLint("SimpleDateFormat")
    fun formatDate(timestamp: String?): String {
        var timestamp = timestamp
        try {
            timestamp = timestamp?.substring(0, 10)
            val lastvisit = SimpleDateFormat("yyyy-MM-dd").parse(timestamp)
            val df: DateFormat = SimpleDateFormat("dd-MM-yyyy")
            timestamp = lastvisit?.let { df.format(it) }
        } catch (_: Exception) { }
        return timestamp?:""
    }
}