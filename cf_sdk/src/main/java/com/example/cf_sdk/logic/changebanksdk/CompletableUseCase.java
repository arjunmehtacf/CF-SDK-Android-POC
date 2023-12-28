package com.example.cf_sdk.logic.changebanksdk;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;

/**
 *
 * Use case to be used when there is no result expected.
 */

public abstract class CompletableUseCase<P extends Parameters> extends UseCase<P, Completable, CompletableObserver> {
    private ExecutionThread mBackgroundExecutionThread;
    protected ExecutionThread mUiExecutionThread;
    protected ChangebankError mErrorHandler;

    protected CompletableUseCase(ExecutionThread backgroundExecutionThread,
                                 ExecutionThread uiExecutionThread,
                                 ChangebankError errorHandler) {
        mBackgroundExecutionThread = backgroundExecutionThread;
        mUiExecutionThread = uiExecutionThread;
        mErrorHandler = errorHandler;
    }

    @Override
    protected abstract Completable buildUseCaseObservable(P parameters);

    @Override
    @SuppressWarnings("unchecked")
    public void execute(CompletableObserver observer, P parameters) {
        buildUseCaseObservable(parameters)
                .subscribeOn(mBackgroundExecutionThread.getScheduler())
                .observeOn(mUiExecutionThread.getScheduler())
                .compose(mErrorHandler)
                .subscribe(observer);
    }
}
