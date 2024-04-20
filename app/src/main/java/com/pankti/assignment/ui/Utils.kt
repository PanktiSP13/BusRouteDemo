package com.pankti.assignment.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {


    fun String.toFormat(): Date {
        return SimpleDateFormat("hh:mm", Locale.getDefault()).parse(this) ?: Date()
    }


    fun String.isBusAvailable(): Boolean {
        return try {

            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            val busDateTimeFormat = format.format(format.parse(this)?: Date())
            val currentTimeFormat = format.format(Date())

            val result = busDateTimeFormat.compareTo(currentTimeFormat)
            result > 0 || result == 0

        } catch (e: Exception) {
            false
        }
    }

}


