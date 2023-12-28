package com.example.cf_sdk.logic.changebanksdk;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

/**
 *
 * Use case to be used when there is only one result expected.
 */

public abstract class SingleUseCase<P extends Parameters, RESULT>
        extends UseCase<P, Single<RESULT>, SingleObserver<RESULT>> {
    private ExecutionThread mBackgroundExecutionThread;
    protected ExecutionThread mUiExecutionThread;
    protected ChangebankError mErrorHandler;

    protected SingleUseCase(ExecutionThread backgroundExecutionThread,
                      ExecutionThread uiExecutionThread,
                      ChangebankError errorHandler) {
        mBackgroundExecutionThread = backgroundExecutionThread;
        mUiExecutionThread = uiExecutionThread;
        mErrorHandler = errorHandler;
    }

    @Override
    protected abstract Single<RESULT> buildUseCaseObservable(P parameters);

    @SuppressWarnings("unchecked")
    @Override
    public void execute(SingleObserver<RESULT> observer, P parameters) {
        buildUseCaseObservable(parameters)
                .subscribeOn(mBackgroundExecutionThread.getScheduler())
                .observeOn(mUiExecutionThread.getScheduler())
                .compose(mErrorHandler)
                .subscribe(observer);
    }
}
