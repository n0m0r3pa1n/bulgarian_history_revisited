package com.nmp90.bghistory.years

import com.google.firebase.firestore.QueryDocumentSnapshot

class YearMapper {
    fun toYear(document: QueryDocumentSnapshot): Year {
        val name = document.getString("name")!!
        val year = document.getDouble("year")!!.toInt()
        return Year(name, year)
    }
}
