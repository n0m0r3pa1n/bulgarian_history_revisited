package com.nmp90.bghistory.events

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine

class EventsRepository(private val db: FirebaseFirestore, private val eventMapper: EventMapper) {
    suspend fun getEvents(topic: Int): List<Event> = suspendCancellableCoroutine { continuation ->
        db.collection("events")
            .whereEqualTo("Topic", topic)
            .orderBy("Year")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val eventList = task.result!!.map { eventMapper.toEvent(it) }
                    continuation.resumeWith(Result.success(eventList))
                } else {
                    continuation.resumeWith(Result.failure(task.exception!!))
                }
            }

    }

    suspend fun getEvent(eventId: String): Event = suspendCancellableCoroutine { continuation ->
        db.collection("events")
            .document(eventId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val event = eventMapper.toEvent(task.result!!)
                    continuation.resumeWith(Result.success(event))
                } else {
                    continuation.resumeWith(Result.failure(task.exception!!))
                }
            }

    }
}
