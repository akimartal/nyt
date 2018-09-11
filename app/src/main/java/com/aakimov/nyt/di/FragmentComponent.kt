package com.aakimov.nyt.di

import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.ui.stories.StoriesFragment
import dagger.Component
import javax.inject.Scope

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class])
interface FragmentComponent {
    fun inject(f: StoriesFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope