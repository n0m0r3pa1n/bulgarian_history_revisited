package com.nmp90.bghistory.myapplication.events

import com.google.firebase.firestore.DocumentSnapshot

class EventMapper {
    fun toEvent(document: DocumentSnapshot): Event {
        val id = document.id
        val title = document.getString("Title")!!
        val place = document.getString("Place")!!
        val leader = document.getString("Leader")
        val year = document.getString("Year")!!
        val result = document.getString("Result")!!
        val description = document.getString("Description")!!
        val topic = document.getDouble("Topic")!!.toInt()

        return Event(
            id,
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
