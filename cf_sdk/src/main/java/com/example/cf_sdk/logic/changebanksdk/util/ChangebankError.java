package com.example.cf_sdk.logic.changebanksdk.util;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

/**
 *
 * Base class for ErrorManager implementation.
 */

public interface ChangebankError<T> extends SingleTransformer<T, T>,
        CompletableTransformer,
        ObservableTransformer<T, T> {
    @Override
    SingleSource<T> apply(Single<T> upstream);

    @Override
    CompletableSource apply(Completable upstream);

    @Override
    ObservableSource<T> apply(Observable<T> upstream);
}