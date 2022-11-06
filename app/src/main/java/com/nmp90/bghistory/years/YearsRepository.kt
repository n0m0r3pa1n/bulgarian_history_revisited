package com.nmp90.bghistory.years

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine

class YearsRepository(private val db: FirebaseFirestore, private val yearMapper: YearMapper) {
    suspend fun getYears(): List<Year> = suspendCancellableCoroutine { continuation ->
        db.collection("years")
            .orderBy("year")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val topics = task.result!!.map { yearMapper.toYear(it) }
                    continuation.resumeWith(Result.success(topics))
                } else {
                    continuation.resumeWith(Result.failure(task.exception!!))
                }
            }
    }

    suspend fun searchYears(query: String): List<Year> =
        suspendCancellableCoroutine { continuation ->
            db.collection("years")
                .orderBy("year")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val topics = task.result!!.map { yearMapper.toYear(it) }
                            .filter { if (!query.isEmpty()) it.name.contains(query) else true }

                        continuation.resumeWith(Result.success(topics))
                    } else {
                        continuation.resumeWith(Result.failure(task.exception!!))
                    }
                }

        }
}
