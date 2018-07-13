package com.aakimov.nyt.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    companion object {
       private var backendDateTimeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale
                .getDefault())

        @JvmStatic
        fun fromBackendDateTime(value: String): Date {
            val date = try {
                backendDateTimeFormat.parse(value)
            } catch (e: ParseException) {
                Log.e(this::class.toString(), "can't parseBackendDateTime")
                Date(0)
            }
            return date
        }

        @JvmStatic
        fun toBackendDateTimeString(value: Date): String {
            return backendDateTimeFormat.format(value)
        }
    }
}
