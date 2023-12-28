package com.example.cf_sdk.logic.changebanksdk.task.preference;

import com.example.cf_sdk.logic.changebanksdk.CompletableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;

/**
 *
 * Use case for adding an address.
 */

public class SetWelcomeDisplayedTask extends CompletableUseCase<Parameters> {
    private final AppPreferenceDatasource mAppPreferenceDatasource;

    @Inject
    public SetWelcomeDisplayedTask(
            @Named("repository") AppPreferenceDatasource appPreferenceDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAppPreferenceDatasource = appPreferenceDatasource;
    }

    @Override
    protected Completable buildUseCaseObservable(Parameters parameters) {
        return mAppPreferenceDatasource.setWelcomeScreenCompletedOrSkipped();
    }
}