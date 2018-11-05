package com.nmp90.bghistory.myapplication.events

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class EventsRepository(private val db: FirebaseFirestore) {
    fun getEvents(): Single<List<String>> {
        return Single.create { emitter ->
            db.collection("events")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val list = mutableListOf<String>()
                        for (document in task.result!!) {
                            list.add(document.id)
                        }
                        emitter.onSuccess(list)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}