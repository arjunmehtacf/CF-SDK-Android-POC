package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.ObservableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.FingerprintResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 *
 * Task to read Fingerprint
 */

public class ReadFingerprintTask extends ObservableUseCase<UpdateCredentialsParameters, FingerprintResponse> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public ReadFingerprintTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                               @Named("io") ExecutionThread backgroundExecutionThread,
                               @Named("ui") ExecutionThread uiExecutionThread,
                               @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Observable<FingerprintResponse> buildUseCaseObservable(UpdateCredentialsParameters updateCredentialsParameters) {
        return mAuthenticationRepository.readFingerprint(updateCredentialsParameters.getUsername());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Observer<FingerprintResponse> fingerprintResponseObserver, UpdateCredentialsParameters parameters) {
        buildUseCaseObservable(parameters)
                .observeOn(mUiExecutionThread.getScheduler())
                .compose(mErrorHandler)
                .subscribe(fingerprintResponseObserver);
    }


}
