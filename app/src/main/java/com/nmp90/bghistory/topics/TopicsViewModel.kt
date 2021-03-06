package com.nmp90.bghistory.topics

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.nmp90.bghistory.R
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import com.nmp90.bghistory.lifecycle.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopicsViewModel constructor(private val topicsRepository: TopicsRepository) : LifecycleViewModel() {

    val displayedChildId = ObservableInt(R.id.pb_loading)
    val topics = MutableLiveData<Response<List<Topic>>>()

    fun loadTopics() = topicsRepository.getTopics()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { displayedChildId.set(R.id.rv_topics) }
        .toReactiveSource()

}
