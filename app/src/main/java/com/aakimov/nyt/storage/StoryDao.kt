package com.aakimov.nyt.storage

import android.arch.persistence.room.*
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
        insert(plainStories)
        val multimedias = stories.flatMap { it.multimedia }.toList()
        insertMultimedias(multimedias)
    }

    @Query("DELETE FROM story")
    abstract fun deleteAllStories()
}
