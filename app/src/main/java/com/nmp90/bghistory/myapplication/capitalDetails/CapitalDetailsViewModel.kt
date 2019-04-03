package com.nmp90.bghistory.myapplication.capitalDetails

import androidx.lifecycle.MutableLiveData
import com.nmp90.bghistory.myapplication.capitals.Capital
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import com.nmp90.bghistory.myapplication.extensions.toReactiveSource
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalDetailsViewModel constructor(private val capitalsRepository: CapitalsRepository) : LifecycleViewModel() {

    val capital: MutableLiveData<Capital> = MutableLiveData()

    fun getCapital(capitalId: String) = capitalsRepository.getCapital(capitalId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toReactiveSource()

}
