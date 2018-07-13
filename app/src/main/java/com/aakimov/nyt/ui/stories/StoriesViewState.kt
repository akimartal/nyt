package com.aakimov.nyt.ui.stories

import com.aakimov.nyt.entity.Story

sealed class StoriesViewState {
    fun reduce(event: StoriesEvent): StoriesViewState {
        return Empty
    }

    object Empty : StoriesViewState()
    object Loading : StoriesViewState()
    data class Success(val news: List<Story>) : StoriesViewState()
    data class Fail(val error: String) : StoriesViewState()

}

