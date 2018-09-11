package com.aakimov.nyt.storage

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Transaction
import com.aakimov.nyt.entity.Multimedia
import com.aakimov.nyt.entity.PlainStory
import com.aakimov.nyt.entity.Story

@Dao
abstract class StoryDao {
    @Insert
    abstract fun insert(plainStories: List<PlainStory>)

    @Insert
    abstract fun insertMultimedias(multimedias: List<Multimedia>)

    @Delete
    abstract fun delete(stories: List<PlainStory>)

    @Transaction
   open fun insertStories(stories: List<Story>) {
        val plainStories = stories.map { it.story }.toList()
        insert(plainStories.filterNotNull())
        val multimedias = stories.flatMap { it.multimedia }.toList()
        insertMultimedias(multimedias)
    }
}
