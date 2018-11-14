package com.nmp90.bghistory.myapplication.extensions

fun String?.isNullOrEmpty() : Boolean {

    return this == null || this.isEmpty()
}
