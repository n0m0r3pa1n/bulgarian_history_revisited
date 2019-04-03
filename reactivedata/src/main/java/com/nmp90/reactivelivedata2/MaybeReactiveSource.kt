package com.nmp90.reactivelivedata2

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicReference


class MaybeReactiveSource<T>(@NonNull private val source: Maybe<T>) : LiveData<Optional<T>>() {

    companion object {
        fun <T> from(@NonNull source: Maybe<T>, subscribeScheduler: Scheduler = Schedulers.io()): LiveData<Optional<T>> {
            return MaybeReactiveSource(source.subscribeOn(subscribeScheduler))
        }
    }

    private val subscriber = AtomicReference<MaybeDataSubscriber>()
    private val observerReference = AtomicReference<Observer<in Optional<T>>>()

    override fun onActive() {
        super.onActive()

        val s = MaybeDataSubscriber()
        subscriber.set(s)
        source.subscribe(s)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in Optional<T>>) {
        super.observe(owner, observer)

        observerReference.compareAndSet(null, observer)
    }

    override fun onInactive() {
        super.onInactive()

        subscriber.getAndSet(null)?.dispose()
        removeObserver(observerReference.getAndSet(null))
    }

    inner class MaybeDataSubscriber : AtomicReference<Disposable>(), MaybeObserver<T> {

        override fun onSuccess(t: T) {
            postValue(Optional.Result(t))

            subscriber.compareAndSet(this, null)
        }

        override fun onComplete() {
            postValue(Optional.Complete())

            subscriber.compareAndSet(this, null)
        }

        override fun onSubscribe(d: Disposable) {
            compareAndSet(null, d)
        }

        override fun onError(e: Throwable) {
            postValue(Optional.Exception(e))

            subscriber.compareAndSet(this, null)
        }

        fun dispose() {
            get()?.dispose()
        }

    }

}
