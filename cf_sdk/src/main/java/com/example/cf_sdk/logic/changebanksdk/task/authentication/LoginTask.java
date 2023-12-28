package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.example.cf_sdk.logic.changebanksdk.validation.LoginValidator;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for user login scenarios.
 */

public class LoginTask extends SingleUseCase<LoginParameters, Session> {

    private final LoginValidator mLoginValidator;
    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public LoginTask(LoginValidator loginValidator,
                     @Named("repository") AuthenticationDatasource authenticationRepository,
                     @Named("io") ExecutionThread backgroundExecutionThread,
                     @Named("ui") ExecutionThread uiExecutionThread,
                     @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mLoginValidator = loginValidator;
        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<Session> buildUseCaseObservable(final LoginParameters parameters) {
        return mLoginValidator.validate(parameters)
                .flatMap(new Function<LoginParameters, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(LoginParameters loginParameters) throws Exception {
                        return mAuthenticationRepository.login(loginParameters);
                    }
                });
    }
}
