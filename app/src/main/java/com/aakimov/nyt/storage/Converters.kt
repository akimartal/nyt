package com.aakimov.nyt.storage

import android.arch.persistence.room.TypeConverter
import com.aakimov.nyt.util.DateTimeUtils
import java.util.*

object Converters {
        @TypeConverter
        @JvmStatic
        fun fromTimestamp(value: String) = DateTimeUtils.fromBackendDateTime(value)

        @TypeConverter
        @JvmStatic
        fun toTimestamp(value: Date) = DateTimeUtils.toBackendDateTimeString(value)

//        @TypeConverter
//        @JvmStatic
//        fun fromListToString(list: List<String>): String {
//            return list.joinToString(separator = ",")
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun toListFromString(data: String): List<String> {
//            return data.split(",")
//        }
}