package com.nmp90.bghistory.myapplication.years

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single


class YearsRepository(private val db: FirebaseFirestore, private val yearMapper: YearMapper) {
    fun getYears(): Single<List<Year>> {
        return Single.create { emitter ->
            db.collection("years")
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
}