package com.nmp90.bghistory.myapplication.eventDetails

import com.nmp90.bghistory.myapplication.events.EventsRepository
import com.nmp90.bghistory.myapplication.extensions.toReactiveSource
import com.nmp90.bghistory.myapplication.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventDetailsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    fun getEvent(eventId: String) = eventsRepository.getEvent(eventId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .toReactiveSource()

}
