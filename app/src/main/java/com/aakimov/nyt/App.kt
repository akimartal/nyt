package com.aakimov.nyt

import android.app.Application
import android.util.Log
import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.di.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class App : Application() {
    lateinit var appComponent: AppComponent
    lateinit var fragmentComponent: FragmentComponent

    init {
        app = this
    }

    @Inject
    lateinit var api: ApiService

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
        fragmentComponent = DaggerFragmentComponent.builder().appComponent(appComponent).build()
        api.stories("home")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },
                        { t -> Log.d("!!! error-", t.toString()) })
    }

    companion object {
        private lateinit var app: App

        fun get(): App {
            return app
        }
    }
}