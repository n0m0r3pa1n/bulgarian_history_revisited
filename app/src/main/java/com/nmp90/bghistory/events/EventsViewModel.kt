package com.nmp90.bghistory.events

import androidx.databinding.ObservableInt
import com.nmp90.bghistory.R
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    val displayedChildId = ObservableInt(R.id.pb_loading)

    fun getEvents(topicId: Int) = eventsRepository.getEvents(topicId)
        .doOnError { exception.set(it) }
        .onErrorReturn { emptyList() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { displayedChildId.set(R.id.rv_events) }
        .toReactiveSource()

}
