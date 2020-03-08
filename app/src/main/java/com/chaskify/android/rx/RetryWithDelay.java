package com.chaskify.android.rx;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class RetryWithDelay implements Function<Flowable<? extends Throwable>, Publisher<?>> {
    private final int retryDelayMillis;

    public RetryWithDelay(final int retryDelayMillis) {
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Publisher<?> apply(final Flowable<? extends Throwable> attempts) {
        return attempts
                .flatMap((Function<Throwable, Flowable<?>>) throwable -> {
                    // When this Observable calls onNext, the original
                    // Observable will be retried (i.e. re-subscribed).
                    return Flowable.timer(retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                });
    }
}