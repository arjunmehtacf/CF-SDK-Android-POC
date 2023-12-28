package com.example.cf_sdk.logic.changebanksdk;

import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 *
 * Use case to be used when there are continuous results expected.
 */

public abstract class ObservableUseCase<P extends Parameters, RESULT>
        extends UseCase<P, Observable<RESULT>, Observer<RESULT>> {
    protected ExecutionThread mBackgroundExecutionThread;
    protected ExecutionThread mUiExecutionThread;
    protected ChangebankError mErrorHandler;

    protected ObservableUseCase(ExecutionThread backgroundExecutionThread,
                                ExecutionThread uiExecutionThread,
                                ChangebankError errorHandler) {
        mBackgroundExecutionThread = backgroundExecutionThread;
        mUiExecutionThread = uiExecutionThread;
        mErrorHandler = errorHandler;
    }

    @Override
    protected Observable<RESULT> buildUseCaseObservable(P parameters) {
        return null;
    }

    @Override
    public abstract void execute(Observer<RESULT> resultObserver, P parameters);
}
