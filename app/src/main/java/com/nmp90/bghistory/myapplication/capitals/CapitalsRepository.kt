package com.nmp90.bghistory.myapplication.capitals

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single


class CapitalsRepository(private val db: FirebaseFirestore, private val capitalMapper: CapitalMapper) {
    fun getCapitals(): Single<List<Capital>> {
        return Single.create { emitter ->
            db.collection("capitals")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val capitalList = task.result!!.map { capitalMapper.toCapital(it) }
                        emitter.onSuccess(capitalList.sortedBy { it.index })
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}
