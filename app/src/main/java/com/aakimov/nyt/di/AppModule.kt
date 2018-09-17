package com.aakimov.nyt.di

import android.app.Application
import android.arch.persistence.room.Room
import com.aakimov.nyt.R
import com.aakimov.nyt.api.ApiRequestInterceptor
import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.storage.Db
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Singleton
    @Provides
    fun provideDb(): Db {
        return Room
                .databaseBuilder(app, Db::class.java, "nyt.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    @Named("url")
    fun provideUrl(): String {
        return app.getString(R.string.api_url)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideApiRequestInterceptor(): ApiRequestInterceptor {
        return ApiRequestInterceptor(app.getString(R.string.api_key))
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(apiRequestInterceptor: ApiRequestInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(apiRequestInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(StethoInterceptor())
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("url") url: String, okHttpClient: OkHttpClient, gson: Gson):
            Retrofit {
        return createRetrofit(url, okHttpClient, gson)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGlide(): RequestManager {
        return Glide.with(app)
    }

    private fun createRetrofit(url: String, okHttpClient: OkHttpClient, gson: Gson) =
            Retrofit.Builder()
                    .client(okHttpClient).baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
}