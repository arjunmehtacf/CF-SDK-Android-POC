package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.GetAccountParameters;
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
 * Task to fetch an account by providing its id.
 */

public class GetAccountByIdTask extends SingleUseCase<GetAccountParameters, Account> {
    private final AccountDatasource mAccountDatasource;
    private final AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    protected GetAccountByIdTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") AccountDatasource accountDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<Account> buildUseCaseObservable(final GetAccountParameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<Account>>>() {
                    @Override
                    public SingleSource<? extends Optional<Account>> apply(Session session) throws Exception {
                        parameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getAccountById(parameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<Account>, MaybeSource<Account>>() {
                    @Override
                    public MaybeSource<Account> apply(Optional<Account> accountOptional) throws Exception {
                        if (accountOptional.isPresent()) {
                            return Maybe.just(accountOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
