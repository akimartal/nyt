package com.aakimov.nyt.storage

import android.arch.persistence.room.TypeConverter
import com.aakimov.nyt.util.DateTimeUtils
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String) = DateTimeUtils.fromBackendDateTime(value)

    @TypeConverter
    fun toTimestamp(value: Date) = DateTimeUtils.toBackendDateTimeString(value)
}