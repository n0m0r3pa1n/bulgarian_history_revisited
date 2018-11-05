package com.nmp90.bghistory.myapplication.events

import com.google.firebase.firestore.QueryDocumentSnapshot

class EventMapper {
    fun toEvent(document: QueryDocumentSnapshot): Event {
        val title = document.getString("title")!!
        val place = document.getString("place")!!
        val leader = document.getString("leader")!!
        val year = document.getString("year")!!
        val result = document.getString("result")!!
        val description = document.getString("description")!!
        val topic = document.getDouble("topic")!!.toInt()

        return Event(
            title,
                    place,
                    leader,
                    year,
                    result,
                    description,
                    topic
        )
    }
}