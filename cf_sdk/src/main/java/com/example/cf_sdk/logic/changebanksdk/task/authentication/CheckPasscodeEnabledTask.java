package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.CheckPasscodeEnabledParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for checking if passcode is enabled for user.
 */

public class CheckPasscodeEnabledTask extends SingleUseCase<CheckPasscodeEnabledParameters, Boolean> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public CheckPasscodeEnabledTask(
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<Boolean> buildUseCaseObservable(CheckPasscodeEnabledParameters parameters) {
        return mAuthenticationRepository.checkPasscodeEnabled(parameters);
    }
}
