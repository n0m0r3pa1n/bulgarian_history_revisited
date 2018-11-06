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

    fun getEvent(eventId: String): Single<Event> {
        return Single.create { emitter ->
            db.collection("events")
                .document(eventId)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val event = eventMapper.toEvent(task.result!!)
                        emitter.onSuccess(event)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}