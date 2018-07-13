package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.aakimov.nyt.bl.repo.StoriesRepository
import com.aakimov.nyt.ui.base.BaseViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class NewsViewModel @Inject constructor(val repository: StoriesRepository) : BaseViewModel() {

    val liveState: LiveData<StoriesViewState>
    private val events: Observer<StoriesEvent>
    private val disposable: Disposable

    init {
        liveState = MutableLiveData<StoriesViewState>()
        events = PublishSubject.create<StoriesEvent>()
        val state = StoriesViewState.Empty
        val reducer = createReducer()
        disposable = events.scan(state, reducer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { liveState.value = it }
    }

    private fun createReducer(): BiFunction<StoriesViewState, in StoriesEvent, StoriesViewState> {
        return BiFunction { state, event -> state.reduce(event) }
    }

    override fun onCleared() {
        disposable?.dispose()
    }

    fun btnClickIntent() {
        events.onNext(StoriesEvent.LoadStories)
    }
}

sealed class StoriesEvent {
    object LoadStories : StoriesEvent()
    data class Test(val i: Int) : StoriesEvent()
}