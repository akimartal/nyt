package com.aakimov.nyt

import android.app.Application
import android.util.Log
import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.di.AppComponent
import com.aakimov.nyt.di.AppModule
import com.aakimov.nyt.di.DaggerAppComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class App : Application() {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var api: ApiService

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
        api.stories("home")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> },
                        { t -> Log.d("!!! error-", t.toString()) })
    }
}