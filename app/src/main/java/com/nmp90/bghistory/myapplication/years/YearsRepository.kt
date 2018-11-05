package com.nmp90.bghistory.myapplication.years

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single


class YearsRepository(private val db: FirebaseFirestore, private val yearMapper: YearMapper) {
    fun getYears(): Single<List<Year>> {
        return Single.create { emitter ->
            db.collection("years")
                .orderBy("year")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val topics = task.result!!.map { yearMapper.toYear(it) }
                        emitter.onSuccess(topics)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }

    fun searchYears(query: String): Single<List<Year>> {
        return Single.create { emitter ->
            db.collection("years")
                .orderBy("year")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val topics = task.result!!.map { yearMapper.toYear(it) }
                            .filter { if(!query.isEmpty()) it.name.contains(query) else true }

                        emitter.onSuccess(topics)
                    } else {
                        emitter.onError(task.exception!!)
                    }
                }

        }
    }
}