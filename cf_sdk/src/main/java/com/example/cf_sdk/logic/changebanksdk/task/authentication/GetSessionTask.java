package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Use case to get session from cache
 */

public class GetSessionTask extends SingleUseCase<Parameters, Session> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public GetSessionTask(
                          @Named("repository") AuthenticationDatasource authenticationRepository,
                          @Named("io") ExecutionThread backgroundExecutionThread,
                          @Named("ui") ExecutionThread uiExecutionThread,
                          @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    public Session executeBlocking(Parameters parameters) {
        return buildUseCaseObservable(parameters).blockingGet();
    }

    @Override
    protected Single<Session> buildUseCaseObservable(final Parameters parameters) {
        return mAuthenticationRepository.getSession()
                .onErrorReturn(new Function<Throwable, Session>() {
                    @Override
                    public Session apply(Throwable throwable) throws Exception {
                        return Session.INVALID_SESSION;
                    }
                });
    }
}
