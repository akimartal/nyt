package com.aakimov.nyt.util

import org.joda.time.format.ISODateTimeFormat
import java.util.*

class DateTimeUtils {
    companion object {
        @JvmStatic
        fun fromBackendDateTime(value: String): Date = ISODateTimeFormat.dateTimeNoMillis()
                .parseDateTime(value).toDate()


        @JvmStatic
        fun toBackendDateTimeString(value: Date): String = ISODateTimeFormat.dateTimeNoMillis()
                .print(value.time)
    }
}
