package com.aakimov.nyt.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aakimov.nyt.ui.stories.StoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: NytViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StoriesViewModel::class)
    internal abstract fun storiesViewModel(viewModel: StoriesViewModel): ViewModel

    //Add more ViewModels here
}