package com.aakimov.nyt.di

import com.aakimov.nyt.ui.stories.StoriesAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class StoriesModule {

    @FragmentScope
    @Provides
    fun provideAdapter(glide: RequestManager): StoriesAdapter {
        return StoriesAdapter(glide)
    }
}