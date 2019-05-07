package com.example.im_blog.http
import io.reactivex.*
import io.reactivex.functions.Function
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit

class GlobalErrorTransformer<T> constructor(
        private val globalOnNextInterceptor: (T) -> Observable<T> = { Observable.just(it) },
        private val globalOnErrorResume: (Throwable) -> Observable<T> = { Observable.error(it) },
        private val retryConfigProvider: (Throwable) -> RetryConfig = { RetryConfig() },
        private val globalDoOnErrorConsumer: (Throwable) -> Unit = { }
) : ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer {

    override fun apply(upstream: Observable<T>): Observable<T> =
            upstream
                    .flatMap {
                        globalOnNextInterceptor(it)
                    }
                    .onErrorResumeNext { throwable: Throwable ->
                        globalOnErrorResume(throwable)
                    }
                    .retryWhen(ObservableRetryDelay(retryConfigProvider))
                    .doOnError(globalDoOnErrorConsumer)

    override fun apply(upstream: Completable): Completable =
            upstream
                    .onErrorResumeNext {
                        globalOnErrorResume(it).ignoreElements()
                    }
                    .retryWhen(FlowableRetryDelay(retryConfigProvider))
                    .doOnError(globalDoOnErrorConsumer)

    override fun apply(upstream: Flowable<T>): Flowable<T> =
            upstream
                    .flatMap {
                        globalOnNextInterceptor(it)
                                .toFlowable(BackpressureStrategy.BUFFER)
                    }
                    .onErrorResumeNext { throwable: Throwable ->
                        globalOnErrorResume(throwable)
                                .toFlowable(BackpressureStrategy.BUFFER)
                    }
                    .retryWhen(FlowableRetryDelay(retryConfigProvider))
                    .doOnError(globalDoOnErrorConsumer)

    override fun apply(upstream: Maybe<T>): Maybe<T> =
            upstream
                    .flatMap {
                        globalOnNextInterceptor(it)
                                .firstElement()
                    }
                    .onErrorResumeNext { throwable: Throwable ->
                        globalOnErrorResume(throwable)
                                .firstElement()
                    }
                    .retryWhen(FlowableRetryDelay(retryConfigProvider))
                    .doOnError(globalDoOnErrorConsumer)

    override fun apply(upstream: Single<T>): Single<T> =
            upstream
                    .flatMap {
                        globalOnNextInterceptor(it)
                                .firstOrError()
                    }
                    .onErrorResumeNext { throwable ->
                        globalOnErrorResume(throwable)
                                .firstOrError()
                    }
                    .retryWhen(FlowableRetryDelay(retryConfigProvider))
                    .doOnError(globalDoOnErrorConsumer)
}

private const val DEFAULT_RETRY_TIMES = 1
private const val DEFAULT_DELAY_DURATION = 1000

data class RetryConfig(val maxRetries: Int = DEFAULT_RETRY_TIMES,
                       val delay: Int = DEFAULT_DELAY_DURATION,
                       val condition: () -> Single<Boolean> = { Single.just(false) })


class ObservableRetryDelay(
    val retryConfigProvider: (Throwable) -> RetryConfig
) : Function<Observable<Throwable>, ObservableSource<*>> {

    private var retryCount: Int = 0

    override fun apply(throwableObs: Observable<Throwable>): ObservableSource<*> {
        return throwableObs
            .flatMap { error ->
                val (maxRetries, delay, retryCondition) = retryConfigProvider(error)

                if (++retryCount <= maxRetries) {
                    retryCondition()
                        .flatMapObservable { retry ->
                            if (retry)
                                Observable.timer(delay.toLong(), TimeUnit.MILLISECONDS)
                            else
                                Observable.error<Any>(error)
                        }
                } else Observable.error<Any>(error)
            }
    }
}


class FlowableRetryDelay(
    val retryConfigProvider: (Throwable) -> RetryConfig
) : Function<Flowable<Throwable>, Publisher<*>> {

    private var retryCount: Int = 0

    override fun apply(throwableFlowable: Flowable<Throwable>): Publisher<*> {
        return throwableFlowable
            .flatMap { error ->
                val (maxRetries, delay, retryTransform) = retryConfigProvider(error)

                if (++retryCount <= maxRetries) {
                    retryTransform()
                        .flatMapPublisher { retry ->
                            if (retry)
                                Flowable.timer(delay.toLong(), TimeUnit.MILLISECONDS)
                            else
                                Flowable.error<Any>(error)
                        }
                } else Flowable.error<Any>(error)
            }
    }
}
