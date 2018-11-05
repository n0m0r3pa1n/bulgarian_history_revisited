package com.nmp90.bghistory.myapplication.events

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class EventsRepository(private val db: FirebaseFirestore, private val eventMapper: EventMapper) {
    fun getEvents(topic: Int): Single<List<Event>> {
        return Single.create { emitter ->
            db.collection("events")
                .whereEqualTo("Topic", topic)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val eventList = task.result!!.map { eventMapper.toEvent(it) }
                        emitter.onSuccess(eventList)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}