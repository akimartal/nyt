package com.aakimov.nyt.api

import com.aakimov.nyt.api.response.NytResponse
import com.aakimov.nyt.api.response.StoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2/{section}.json")
    fun stories(@Path("section") section: String): Observable<NytResponse<List<StoryResponse>>>
}
