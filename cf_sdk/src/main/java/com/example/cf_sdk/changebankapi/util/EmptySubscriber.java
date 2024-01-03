package com.example.cf_sdk.changebankapi.util;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

/**
 *
 * Empty observer.
 */

public class EmptySubscriber implements CompletableObserver {
    public static final String TAG = EmptySubscriber.class.getSimpleName();

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
