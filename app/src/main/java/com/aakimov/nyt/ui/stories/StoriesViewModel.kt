package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.MutableLiveData
import com.aakimov.nyt.bl.repo.StoriesRepository
import com.aakimov.nyt.ui.base.BaseEvent
import com.aakimov.nyt.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import timber.log.Timber
import javax.inject.Inject

class StoriesViewModel @Inject constructor(val repository: StoriesRepository) :
        BaseViewModel<StoriesEvent>() {

    val state: MutableLiveData<StoriesViewState> = MutableLiveData()
    private lateinit var disposable: Disposable

    private fun createReducer(): BiFunction<StoriesViewState, in StoriesEvent, StoriesViewState> {
        return BiFunction { state, event -> state.reduce(event) }
    }

    override fun onCleared() {
        disposable.dispose()
    }

    override fun observeEvents(observable: Observable<StoriesEvent>) {
        val state = StoriesViewState.Empty
        val reducer = createReducer()

        disposable = observable
                .doOnNext { processEvent(it) }
                .scan(state, reducer)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Got state $it")
                    this.state.value = it
                }, { Timber.e(it) })
    }

    private fun processEvent(event: StoriesEvent) {
        when (event) {
            is StoriesEvent.LoadStories ->{}
            is StoriesEvent.StoryDetails ->{Timber.d("Story guid - ${event.storyGuid}")}
        }
    }
}

sealed class StoriesEvent : BaseEvent() {
    object LoadStories : StoriesEvent()
    data class StoryDetails(val storyGuid: String) : StoriesEvent()
}