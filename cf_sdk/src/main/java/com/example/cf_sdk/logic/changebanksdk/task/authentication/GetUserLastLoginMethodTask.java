package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for getting user last login method.
 */

public class GetUserLastLoginMethodTask extends SingleUseCase<Parameters, LoginParameters.LoginMethodType> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public GetUserLastLoginMethodTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                      @Named("io") ExecutionThread backgroundExecutionThread,
                                      @Named("ui") ExecutionThread uiExecutionThread,
                                      @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<LoginParameters.LoginMethodType> buildUseCaseObservable(final Parameters parameters) {
        return mAuthenticationRepository.getLoginMethodType();
    }
}
