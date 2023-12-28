package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get member accounts
 */

public class GetAccountsTask extends SingleUseCase<Parameters, AccountsApiResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetAccountsTask(@Named("repository") AccountDatasource accountDatasource,
                           @Named("repository") AuthenticationDatasource authenticationDatasource,
                           @Named("io") ExecutionThread backgroundExecutionThread,
                           @Named("ui") ExecutionThread uiExecutionThread,
                           @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AccountsApiResponse> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<AccountsApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<AccountsApiResponse>> apply(Session session) throws Exception {
                        StringParameters stringParameters = StringParameters.create(Long.toString(session.getMemberId()));
                        stringParameters.addToken(session.getTokenType()+" "+session.getToken());
                        return mAccountDatasource.getMemberAccounts(stringParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<AccountsApiResponse>, MaybeSource<AccountsApiResponse>>() {
                    @Override
                    public MaybeSource<AccountsApiResponse> apply(Optional<AccountsApiResponse> accountsApiResponseOptional) throws Exception {
                        if (accountsApiResponseOptional.isPresent()) {
                            return Maybe.just(accountsApiResponseOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
