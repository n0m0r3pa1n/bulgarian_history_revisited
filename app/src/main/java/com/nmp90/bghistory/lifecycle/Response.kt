package com.nmp90.bghistory.lifecycle

import androidx.annotation.NonNull

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

class Response<T: Any> private constructor(
    val status: Status,
    val data: T?,
    val error: Throwable?
) {
    companion object {

        fun <T: Any> success(@NonNull data: T): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T: Any> error(@NonNull error: Throwable): Response<T> {
            return Response(Status.ERROR, null, error)
        }
    }
}
