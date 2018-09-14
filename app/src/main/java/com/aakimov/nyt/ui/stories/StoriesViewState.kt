package com.aakimov.nyt.ui.stories

import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.ui.base.BaseViewState

data class StoriesViewState(val isLoading: Boolean = false, val errorText: String = "",
                            val stories: List<Story> = emptyList())
    : BaseViewState {

    fun reduce(event: StoriesEvent): StoriesViewState =
            when (event) {
                is StoriesEvent.LoadStories -> copy(isLoading = true, errorText = "")
                is StoriesEvent.StoriesLoaded -> copy(isLoading = false, stories = event.stories)
                is StoriesEvent.StoriesLoadedWithError -> copy(isLoading = false,
                        errorText = event.errorText)
            }

    override fun toString(): String {
        return "StoriesViewState(isLoading=$isLoading, errorText='$errorText', stories " +
                "size=${stories.size})"
    }

}

