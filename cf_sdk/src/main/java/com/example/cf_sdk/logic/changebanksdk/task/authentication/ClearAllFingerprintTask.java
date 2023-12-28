package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.CompletableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;

/**
 *
 * Task to clear all Fingerprint
 */

public class ClearAllFingerprintTask extends CompletableUseCase<Parameters> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public ClearAllFingerprintTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                   @Named("io") ExecutionThread backgroundExecutionThread,
                                   @Named("ui") ExecutionThread uiExecutionThread,
                                   @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Completable buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationRepository.clearAllFingerprint();
    }


}
