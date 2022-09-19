package com.app.obvious.utils

import android.annotation.SuppressLint
import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

    //Converting json file (stored in raw folder) in string format
    fun fetchData(context: Context): String? {
        return try {
            val inputStream = context.assets.open("data.json")
            val sizeOfJSONFile = inputStream.available()
            val bytes = ByteArray(sizeOfJSONFile)
            inputStream.read(bytes)
            inputStream.close()
            String(bytes, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}