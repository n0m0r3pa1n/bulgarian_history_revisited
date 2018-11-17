package com.nmp90.bghistory.myapplication.capitals

import com.google.firebase.firestore.DocumentSnapshot

class CapitalMapper {
    fun toCapital(document: DocumentSnapshot): Capital {
        val id = document.id
        val index = document.getDouble("Index")!!.toInt()
        val name = document.getString("Name")!!
        val period = document.getString("Period")!!
        val picture = document.getString("Picture")!!
        val content = document.getString("Content")!!
        val lat = document.getString("Lat")!!
        val lng = document.getString("Lng")!!
        val citizens = document.getDouble("Citizens")!!
        return Capital(
            id, index, name, period, picture, content, lat, lng, citizens.toInt()
        )
    }
}
