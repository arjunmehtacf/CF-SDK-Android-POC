package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.response.BankCredentialsApiResponse;
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
 * Task to retrieve banks.
 */

public class BankCredentialsTask extends SingleUseCase<StringParameters, BankCredentialsApiResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public BankCredentialsTask(@Named("repository") AccountDatasource accountDatasource,
                               @Named("repository") AuthenticationDatasource authenticationDatasource,
                               @Named("io") ExecutionThread backgroundExecutionThread,
                               @Named("ui") ExecutionThread uiExecutionThread,
                               @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<BankCredentialsApiResponse> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<BankCredentialsApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<BankCredentialsApiResponse>> apply(Session session) throws Exception {
                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getBankCredentials(stringParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<BankCredentialsApiResponse>, MaybeSource<BankCredentialsApiResponse>>() {
                    @Override
                    public MaybeSource<BankCredentialsApiResponse> apply(Optional<BankCredentialsApiResponse> bankCredentialsApiResponseOptional) throws Exception {
                        if (bankCredentialsApiResponseOptional.isPresent()) {
                            return Maybe.just(bankCredentialsApiResponseOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
