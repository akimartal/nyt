package com.aakimov.nyt.di

import com.aakimov.nyt.App
import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.storage.Db
import com.aakimov.nyt.ui.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(activity: BaseActivity)
    fun apiService(): ApiService
    fun db(): Db
}