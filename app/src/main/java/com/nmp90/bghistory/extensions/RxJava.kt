package com.nmp90.bghistory.extensions

import androidx.lifecycle.LiveDataReactiveStreams
import com.nmp90.reactivelivedata2.SingleReactiveSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun <T: Any> Single<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this.toFlowable())

fun <T: Any> Single<T>.toReactiveSource() = SingleReactiveSource.from(this)

fun Disposable.disposeWith(disposable: CompositeDisposable) = disposable.add(this)
