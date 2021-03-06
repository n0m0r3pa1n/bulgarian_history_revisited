package com.nmp90.reactivelivedata2

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.jetbrains.annotations.TestOnly

inline fun <T> LiveData<Optional<T>>.subscribeSingle(owner: LifecycleOwner, crossinline onSuccess: (t: T) -> Unit, crossinline onError: (e: Throwable) -> Unit = {}) {
    this.observe(owner, Observer {
        if (it != null) {
            when (it) {
                is Optional.Result<T> -> onSuccess(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

inline fun <T> LiveData<Optional<T>>.subscribeMaybe(owner: LifecycleOwner, crossinline onSuccess: (t: T) -> Unit, crossinline onError: (e: Throwable) -> Unit = {}, crossinline onComplete: () -> Unit = {}) {
    this.observe(owner, Observer {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Result<T> -> onSuccess(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

inline fun <T> LiveData<Optional<T>>.subscribeFlowable(owner: LifecycleOwner, crossinline onNext: (t: T) -> Unit, crossinline onError: (e: Throwable) -> Unit = {}, crossinline onComplete: () -> Unit = {}) {
    this.observe(owner, Observer {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Result<T> -> onNext(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

inline fun LiveData<Optional<Nothing>>.subscribeCompletable(owner: LifecycleOwner, crossinline onComplete: () -> Unit = {}, crossinline onError: (e: Throwable) -> Unit = {}) {
    this.observe(owner, Observer {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

inline fun <T> LiveData<T>.nonNullObserver(owner: LifecycleOwner, crossinline observer: (t: T) -> Unit, crossinline nullObserver: () -> Unit = {}) {
    this.observe(owner, Observer {
        if (it != null) {
            observer(it)
        } else {
            nullObserver()
        }
    })
}

//region test env.
@TestOnly
inline fun <T> LiveData<Optional<T>>.testSingleSubscribe(owner: Lifecycle, crossinline onSuccess: (t: T) -> Unit = {}, crossinline onError: (e: Throwable) -> Unit = {}) {
    this.observe({ owner }, {
        if (it != null) {
            when (it) {
                is Optional.Result<T> -> onSuccess(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

@TestOnly
inline fun <T> LiveData<Optional<T>>.testMaybeSubscribe(owner: Lifecycle, crossinline onSuccess: (t: T) -> Unit = {}, crossinline onError: (e: Throwable) -> Unit = {}, crossinline onComplete: () -> Unit = {}) {
    this.observe({ owner }, {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Result<T> -> onSuccess(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

@TestOnly
inline fun <T> LiveData<Optional<T>>.testFlowableSubscribe(owner: Lifecycle, crossinline onNext: (t: T) -> Unit = {}, crossinline onError: (e: Throwable) -> Unit = {}, crossinline onComplete: () -> Unit = {}) {
    this.observe({ owner }, {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Result<T> -> onNext(it.result)
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}

@TestOnly
inline fun LiveData<Optional<Nothing>>.testCompletableSubscribe(owner: Lifecycle, crossinline onComplete: () -> Unit = {}, crossinline onError: (e: Throwable) -> Unit = {}) {
    this.observe({ owner }, {
        if (it != null) {
            when (it) {
                is Optional.Complete -> onComplete()
                is Optional.Exception -> onError(it.throwable)
            }
        }
    })
}
//endregion
