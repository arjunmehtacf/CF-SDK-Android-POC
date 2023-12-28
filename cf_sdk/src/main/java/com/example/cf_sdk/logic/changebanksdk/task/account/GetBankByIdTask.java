package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.BankParameters;
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
 * Use case for get bank by id.
 */

public class GetBankByIdTask extends SingleUseCase<BankParameters, Bank> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetBankByIdTask(@Named("repository") AccountDatasource accountDatasource,
                           @Named("repository") AuthenticationDatasource authenticationDatasource,
                           @Named("io") ExecutionThread backgroundExecutionThread,
                           @Named("ui") ExecutionThread uiExecutionThread,
                           @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<Bank> buildUseCaseObservable(final BankParameters bankParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<Bank>>>() {
                    @Override
                    public SingleSource<? extends Optional<Bank>> apply(Session session) throws Exception {
                        bankParameters.addHeader(Header.TOKEN, session.getToken());
                        return mAccountDatasource.getBank(bankParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<Bank>, MaybeSource<Bank>>() {
                    @Override
                    public MaybeSource<Bank> apply(Optional<Bank> bankOptional) throws Exception {
                        if (bankOptional.isPresent()) {
                            return Maybe.just(bankOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle();
    }
}
