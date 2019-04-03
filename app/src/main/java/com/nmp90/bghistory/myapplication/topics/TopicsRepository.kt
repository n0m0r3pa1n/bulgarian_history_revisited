package com.nmp90.bghistory.myapplication.topics

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single


class TopicsRepository(private val db: FirebaseFirestore, private val topicMapper: TopicMapper) {
    fun getTopics(): Single<List<Topic>> {
        return Single.create { emitter ->
            db.collection("topics")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val topics = task.result!!.map { topicMapper.toTopic(it) }
                            .sortedBy { it.id }
                        emitter.onSuccess(topics)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}
