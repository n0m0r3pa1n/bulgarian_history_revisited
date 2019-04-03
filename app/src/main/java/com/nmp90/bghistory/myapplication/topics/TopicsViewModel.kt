package com.nmp90.bghistory.myapplication.topics

import com.nmp90.bghistory.myapplication.extensions.toLiveData
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopicsViewModel constructor(private val topicsRepository: TopicsRepository) : LifecycleViewModel() {

    fun getTopics() = topicsRepository.getTopics()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

}
