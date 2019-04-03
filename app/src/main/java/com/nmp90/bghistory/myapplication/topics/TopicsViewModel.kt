package com.nmp90.bghistory.myapplication.topics

import androidx.databinding.ObservableInt
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.extensions.toLiveData
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopicsViewModel constructor(private val topicsRepository: TopicsRepository) : LifecycleViewModel() {

    val displayedChildId = ObservableInt(R.id.pb_loading)

    fun getTopics() = topicsRepository.getTopics()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { displayedChildId.set(R.id.rv_topics) }
        .toLiveData()

}
