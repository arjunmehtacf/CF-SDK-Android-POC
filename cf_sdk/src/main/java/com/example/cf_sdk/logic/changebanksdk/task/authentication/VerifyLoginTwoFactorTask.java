package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to verify two factor code for login.
 */

public class VerifyLoginTwoFactorTask extends SingleUseCase<TwoFactorParameters, Session> {

    private final AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public VerifyLoginTwoFactorTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                    @Named("io") ExecutionThread backgroundExecutionThread,
                                    @Named("ui") ExecutionThread uiExecutionThread,
                                    @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<Session> buildUseCaseObservable(final TwoFactorParameters twoFactorParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(Session session) throws Exception {
                        TwoFactorParameters parameters = twoFactorParameters.withMemberId(session.getMemberId());
                        parameters.addToken(session.getToken());
                        return mAuthenticationRepository.verifyLoginMFA(parameters);
                    }
                });
    }


}
