package com.aakimov.nyt.util

import org.joda.time.format.ISODateTimeFormat
import timber.log.Timber
import java.text.ParseException
import java.util.*

class DateTimeUtils {
    companion object {


        @JvmStatic
        fun fromBackendDateTime(value: String): Date {
            return try {
                Timber.d("parseBackendDateTime $value")
                ISODateTimeFormat.dateTimeNoMillis().parseDateTime(value).toDate()
            } catch (e: ParseException) {
                Timber.e("can't parseBackendDateTime $value")
                Date(0)
            }

        }

        @JvmStatic
        fun toBackendDateTimeString(value: Date): String {
            return ISODateTimeFormat.dateTimeNoMillis().print(value.time)
        }
    }
}
