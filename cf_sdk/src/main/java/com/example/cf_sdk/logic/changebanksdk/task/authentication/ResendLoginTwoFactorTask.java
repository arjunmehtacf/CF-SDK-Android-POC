package com.example.cf_sdk.logic.changebanksdk.task.authentication;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.ResendTwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to resend two factor code for login.
 */

public class ResendLoginTwoFactorTask extends SingleUseCase<ResendTwoFactorParameters, Session> {

    private AuthenticationDatasource mAuthenticationRepository;

    @Inject
    public ResendLoginTwoFactorTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                                    @Named("io") ExecutionThread backgroundExecutionThread,
                                    @Named("ui") ExecutionThread uiExecutionThread,
                                    @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
    }

    @Override
    protected Single<Session> buildUseCaseObservable(final ResendTwoFactorParameters resendTwoFactorParameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(Session session) throws Exception {
                        resendTwoFactorParameters.addToken(session.getToken());
                        resendTwoFactorParameters.setMemberId(session.getMemberId());
                        return mAuthenticationRepository.resendLoginMFA(resendTwoFactorParameters);
                    }
                });
    }


}
