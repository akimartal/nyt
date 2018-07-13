package com.aakimov.nyt.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ApiRequestInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder().url(modifiedUrl(request.url())).build())
    }

    private fun modifiedUrl(initialUrl: HttpUrl): HttpUrl {
        return initialUrl.newBuilder().addQueryParameter("api-key", apiKey).build()
    }
}