package com.app.obvious.utils

import android.content.Context
import android.widget.Toast
import java.io.IOException

fun Context.showToast(msg:String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

//Converting json file (stored in raw folder) in string format
fun Context.fetchData(): String? {
    return try {
        val inputStream = assets.open("data.json")
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