package com.example.cf_sdk.changebankapi.util

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

/**
 *
 * Base class for ErrorManager implementation.
 */
interface ChangebankError<T> : SingleTransformer<T, T>, CompletableTransformer,
    ObservableTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T>
    override fun apply(upstream: Completable): CompletableSource
    override fun apply(upstream: Observable<T>): ObservableSource<T>
}