package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
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
 * Task for Refreshing Ach Accounts
 */

public class RefreshAchAccountsTask extends SingleUseCase<Parameters, ChangebankResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public RefreshAchAccountsTask(@Named("repository") AccountDatasource accountDatasource,
                                  @Named("repository") AuthenticationDatasource authenticationDatasource,
                                  @Named("io") ExecutionThread backgroundExecutionThread,
                                  @Named("ui") ExecutionThread uiExecutionThread,
                                  @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<ChangebankResponse> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Session session) throws Exception {
                        StringParameters stringParameters = StringParameters.create(Long.toString(session.getMemberId()));
                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.refreshAchAccounts(stringParameters);
                    }
                });
    }
}
