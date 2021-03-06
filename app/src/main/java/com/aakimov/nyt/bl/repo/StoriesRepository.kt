package com.aakimov.nyt.bl.repo

import com.aakimov.nyt.api.ApiService
import com.aakimov.nyt.bl.StoriesTransformer
import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.storage.Db
import com.aakimov.nyt.ui.stories.StoriesEvent
import com.aakimov.nyt.ui.stories.StoriesViewState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoriesRepository @Inject constructor(val api: ApiService, val db: Db) {

    fun news(section: String): Observable<StoriesEvent> {
        return api.stories(section)
                .compose(StoriesTransformer())
                .doOnNext { saveToDb(it) }
                .subscribeOn(Schedulers.io())
                .map<StoriesEvent> { StoriesEvent.StoriesLoaded(it) }
                .startWith(StoriesEvent.LoadStories)
                .onErrorReturn { StoriesEvent.StoriesLoadedWithError(it.localizedMessage) }
    }

    private fun saveToDb(list: List<Story>) {
        db.storyDao().deleteAllStories()
        db.storyDao().insertStories(list)
    }
}