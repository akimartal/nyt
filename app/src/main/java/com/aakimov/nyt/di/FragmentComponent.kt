package com.aakimov.nyt.di

import com.aakimov.nyt.ui.stories.StoriesFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class, StoriesModule::class])
interface FragmentComponent {
    fun inject(f: StoriesFragment)
}

