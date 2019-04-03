package com.nmp90.bghistory.myapplication.events

import com.nmp90.bghistory.myapplication.extensions.toLiveData
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    fun getEvents(topicId: Int) = eventsRepository.getEvents(topicId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

}
