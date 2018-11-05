package com.nmp90.bghistory.myapplication.years

import com.google.firebase.firestore.QueryDocumentSnapshot

class YearMapper {
    fun toYear(document: QueryDocumentSnapshot): Year {
        val name = document.getString("name")!!
        return Year(name)
    }
}