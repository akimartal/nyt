package com.aakimov.nyt.storage

import android.arch.persistence.room.TypeConverter
import com.aakimov.nyt.util.DateTimeUtils
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestamp(value: String) = DateTimeUtils.fromBackendDateTime(value)

    @TypeConverter
    fun toTimestamp(value: Date) = DateTimeUtils.toBackendDateTimeString(value)

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toListFromString(data: String): List<String> {
        return data.split(",")
    }
}