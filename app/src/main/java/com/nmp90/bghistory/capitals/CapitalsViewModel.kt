package com.nmp90.bghistory.capitals

import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
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
