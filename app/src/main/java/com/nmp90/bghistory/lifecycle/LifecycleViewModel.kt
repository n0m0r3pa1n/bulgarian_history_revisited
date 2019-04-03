package com.nmp90.bghistory.lifecycle

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class LifecycleViewModel : ViewModel() {
    val exception = ObservableField<Throwable>()
    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
