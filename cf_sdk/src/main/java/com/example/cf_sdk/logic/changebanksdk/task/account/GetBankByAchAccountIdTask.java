package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.BankParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get Bank by first getting ach account by id then using bankid to get bank by id.
 */

public class GetBankByAchAccountIdTask extends SingleUseCase<StringParameters, Bank> {
    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetBankByAchAccountIdTask(@Named("repository") AccountDatasource accountDatasource,
                                     @Named("repository") AuthenticationDatasource authenticationDatasource,
                                     @Named("io") ExecutionThread backgroundExecutionThread,
                                     @Named("ui") ExecutionThread uiExecutionThread,
                                     @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<Bank> buildUseCaseObservable(final StringParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Bank>>() {
                    @Override
                    public SingleSource<? extends Bank> apply(final Session session) throws Exception {
                        stringParameters.addToken(session.getToken());
                        return mAccountDatasource.getAchAccount(stringParameters)
                                .flatMap(new Function<AchAccount, SingleSource<? extends Optional<Bank>>>() {
                                    @Override
                                    public SingleSource<? extends Optional<Bank>> apply(AchAccount achAccount) throws Exception {
                                        BankParameters bankParameters = BankParameters.create(new HashMap<String, String>(), achAccount.getBankId());
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
                });
    }
}
