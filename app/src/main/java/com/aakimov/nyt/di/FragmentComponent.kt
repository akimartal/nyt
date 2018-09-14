package com.aakimov.nyt.di

import com.aakimov.nyt.ui.base.BaseFragment
import com.aakimov.nyt.ui.details.DetailsFragment
import com.aakimov.nyt.ui.stories.StoriesFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class, StoriesModule::class])
interface FragmentComponent {
    fun inject(f: StoriesFragment)
    fun inject(f: DetailsFragment)
}

