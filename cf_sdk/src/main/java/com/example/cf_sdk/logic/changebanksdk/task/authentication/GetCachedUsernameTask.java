package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Task to get Cached Username
 */

public class GetCachedUsernameTask extends SingleUseCase<Parameters, Optional<String>> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    GetCachedUsernameTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                          @Named("io") ExecutionThread backgroundExecutionThread,
                          @Named("ui") ExecutionThread uiExecutionThread,
                          @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<Optional<String>> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationRepository.getCachedUsername();
    }

}
