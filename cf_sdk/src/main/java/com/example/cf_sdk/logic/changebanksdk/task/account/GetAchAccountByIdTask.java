package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get Ach Account by id.
 */

public class GetAchAccountByIdTask extends SingleUseCase<StringParameters, AchAccount> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetAchAccountByIdTask(@Named("repository") AccountDatasource accountDatasource,
                                 @Named("repository") AuthenticationDatasource authenticationDatasource,
                                 @Named("io") ExecutionThread backgroundExecutionThread,
                                 @Named("ui") ExecutionThread uiExecutionThread,
                                 @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchAccount> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends AchAccount>>() {
                    @Override
                    public SingleSource<? extends AchAccount> apply(Session session) throws Exception {
                        stringParameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mAccountDatasource.getAchAccount(stringParameters);
                    }
                });

    }
}
