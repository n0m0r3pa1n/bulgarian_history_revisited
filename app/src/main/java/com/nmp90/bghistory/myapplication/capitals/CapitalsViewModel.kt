package com.nmp90.bghistory.myapplication.capitals

import com.nmp90.bghistory.myapplication.extensions.toLiveData
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalsViewModel constructor(private val capitalsRepository: CapitalsRepository) : LifecycleViewModel() {

    fun getCapitals() = capitalsRepository.getCapitals()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

}
