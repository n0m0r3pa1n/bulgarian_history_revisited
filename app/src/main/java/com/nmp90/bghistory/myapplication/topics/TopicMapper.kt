package com.nmp90.bghistory.myapplication.topics

import com.google.firebase.firestore.QueryDocumentSnapshot

class TopicMapper {
    fun toTopic(document: QueryDocumentSnapshot): Topic {
        val id = document.getDouble("ID")!!.toInt()
        val name = document.getString("Name")!!

        return Topic(id, name)
    }
}