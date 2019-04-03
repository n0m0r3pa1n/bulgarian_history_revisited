package com.nmp90.bghistory.eventDetails

import androidx.databinding.ObservableInt
import com.nmp90.bghistory.R
import com.nmp90.bghistory.events.EventsRepository
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventDetailsViewModel constructor(private val eventsRepository: EventsRepository) : LifecycleViewModel() {

    val childId = ObservableInt(R.id.loader)

    fun getEvent(eventId: String) = eventsRepository.getEvent(eventId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { childId.set(R.id.content) }
        .toReactiveSource()

}
