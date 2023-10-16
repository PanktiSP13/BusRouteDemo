package com.pankti.assignment.ui

import android.content.Context
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

object Utils {

    fun readJsonFromAssets(context: Context, fileName: String): String {
        val json: String
        try {
            val inputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return json
    }

    fun String.toFormat(): Date {
        return try { SimpleDateFormat("hh:mm").parse(this) }catch (e:Exception){ Date()}
    }

    fun Date.isBusAvailable():Boolean{
       return if (this.after(Date()) || this == Date()) return true else false
    }
}