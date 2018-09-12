package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.MutableLiveData
import com.aakimov.nyt.bl.repo.StoriesRepository
import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.ui.base.BaseEvent
import com.aakimov.nyt.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class StoriesViewModel @Inject constructor(val repository: StoriesRepository) :
        BaseViewModel<StoriesEvent>() {

    private val eventsSubject = PublishSubject.create<StoriesEvent>()
    val state: MutableLiveData<StoriesViewState> = MutableLiveData()
    private var disposable: Disposable

    init {
        val state = StoriesViewState.Empty
        val reducer = createReducer()

        disposable = eventsSubject
                .scan(state, reducer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Got state ${it.javaClass.simpleName}")
                    this.state.value = it
                }, { Timber.e(it) })
    }

    private fun createReducer(): BiFunction<StoriesViewState, in StoriesEvent, StoriesViewState> {
        return BiFunction { state, event -> state.reduce(event) }
    }

    override fun onCleared() {
        disposable.dispose()
    }

    fun observeLoadStories(loadStoriesObservable: Observable<Boolean>) {
        loadStoriesObservable
                .flatMap { repository.news("technology") }
                .subscribe(eventsSubject)
    }
}

sealed class StoriesEvent : BaseEvent() {
    object LoadStories : StoriesEvent()
    data class StoriesLoaded(val stories: List<Story>) : StoriesEvent()
    data class StoriesLoadedWithError(val errorText: String) : StoriesEvent()
}