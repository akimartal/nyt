package com.aakimov.nyt.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.aakimov.nyt.entity.Multimedia
import com.aakimov.nyt.entity.PlainStory
import retrofit2.Converter

@Database(entities = [PlainStory::class, Multimedia::class], version = 1)
@TypeConverters(Converter::class)
abstract class Db : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}