package com.nmp90.bghistory.myapplication.capitals

import com.nmp90.bghistory.myapplication.extensions.toReactiveSource
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalsViewModel constructor(private val capitalsRepository: CapitalsRepository) : LifecycleViewModel() {

    fun getCapitals() = capitalsRepository.getCapitals()
        .doOnError { exception.set(it) }
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toReactiveSource()

}
