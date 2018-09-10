package com.aakimov.nyt.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

abstract class BaseViewModel<E : BaseEvent> : ViewModel() {
    abstract fun observeEvents(observable: Observable<E>)
}