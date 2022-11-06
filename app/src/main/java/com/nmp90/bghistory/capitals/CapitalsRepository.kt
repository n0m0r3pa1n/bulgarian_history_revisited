package com.nmp90.bghistory.capitals

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single
import kotlinx.coroutines.suspendCancellableCoroutine


class CapitalsRepository(
    private val db: FirebaseFirestore,
    private val capitalMapper: CapitalMapper
) {
    suspend fun getCapitals(): List<Capital> = suspendCancellableCoroutine { continuation ->
        db.collection("capitals")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val capitalList = task.result!!.map { capitalMapper.toCapital(it) }
                    continuation.resumeWith(Result.success(capitalList.sortedBy { it.index }))
                } else {
                    continuation.resumeWith(Result.failure(task.exception!!))
                }
            }
    }

    suspend fun getCapital(capitalId: String): Capital = suspendCancellableCoroutine { continuation ->
            db.collection("capitals")
                .document(capitalId)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val capital = capitalMapper.toCapital(task.result!!)
                        continuation.resumeWith(Result.success(capital))
                    } else {
                        continuation.resumeWith(Result.failure(task.exception!!))
                    }
                }

        }
}
