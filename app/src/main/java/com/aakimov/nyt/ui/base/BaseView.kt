package com.aakimov.nyt.ui.base

import io.reactivex.Observable

interface BaseView<E : BaseEvent, S : BaseViewState> {
    fun render(state: S)
}