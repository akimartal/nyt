package com.aakimov.nyt.ui.stories

import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.ui.base.BaseViewState

sealed class StoriesViewState : BaseViewState {
    fun reduce(event: StoriesEvent): StoriesViewState =
            when (event) {
                is StoriesEvent.LoadStories -> Loading
                is StoriesEvent.StoriesLoaded -> Success(event.stories)
                is StoriesEvent.StoriesLoadedWithError -> Fail(event.errorText)
            }

    object Empty : StoriesViewState()
    object Loading : StoriesViewState()
    data class Success(val stories: List<Story>) : StoriesViewState()
    data class Fail(val error: String) : StoriesViewState()

}

