package com.nmp90.bghistory.myapplication.years

import com.nmp90.bghistory.myapplication.extensions.toLiveData
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class YearsViewModel constructor(private val yearsRepository: YearsRepository) : LifecycleViewModel() {

    fun getYears() = yearsRepository.getYears()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

    fun searchYears(query: String) = yearsRepository.searchYears(query)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

}
