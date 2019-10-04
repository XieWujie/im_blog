package com.example.im_blog.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Scheduler
import io.reactivex.android.MainThreadDisposable
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> LiveData<T>.toReactiveStream(
    observerScheduler: Scheduler = AndroidSchedulers.mainThread()
): Flowable<T> = Flowable
    .create({ emitter: FlowableEmitter<T> ->
        val observer = Observer<T> { data ->
            data?.let {
                emitter.onNext(it)
            }
        }
        observeForever(observer)

        emitter.setCancellable {
            object : MainThreadDisposable() {
                override fun onDispose() {
                    removeObserver(observer)
                }
            }
        }
    }, BackpressureStrategy.LATEST)
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(observerScheduler)