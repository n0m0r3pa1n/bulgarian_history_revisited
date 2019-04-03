package com.nmp90.bghistory.myapplication.lifecycle

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class LifecycleViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
