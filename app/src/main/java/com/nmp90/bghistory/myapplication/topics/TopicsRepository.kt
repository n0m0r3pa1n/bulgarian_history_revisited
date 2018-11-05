package com.nmp90.bghistory.myapplication.topics

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single


class TopicsRepository(private val db: FirebaseFirestore) {
    fun getTopics(): Single<List<String>> {
        return Single.create { emitter ->
            db.collection("topics")
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