package com.nmp90.bghistory.myapplication.extensions

import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single

fun <T: Any> Single<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this.toFlowable())
