package com.nmp90.bghistory.topics

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine


class TopicsRepository(private val db: FirebaseFirestore, private val topicMapper: TopicMapper) {
    suspend fun getTopics(): List<Topic> = suspendCancellableCoroutine { continuation ->
        db.collection("topics")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val topics = task.result!!.map { topicMapper.toTopic(it) }
                        .sortedBy { it.id }

                    continuation.resumeWith(Result.success(topics))
                } else {
                    continuation.resumeWith(Result.failure(task.exception!!))
                }
            }
    }
}
