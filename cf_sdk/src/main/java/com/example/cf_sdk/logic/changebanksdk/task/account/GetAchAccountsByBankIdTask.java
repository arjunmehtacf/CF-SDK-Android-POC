package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.AchAccount;
import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.example.cf_sdk.logic.changebanksdk.model.account.BankType;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.BankParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AchAccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task to get an Ach Account by id.
 */

public class GetAchAccountsByBankIdTask extends SingleUseCase<BankParameters, AchAccountsApiResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetAchAccountsByBankIdTask(@Named("repository") AccountDatasource accountDatasource,
                                      @Named("repository") AuthenticationDatasource authenticationDatasource,
                                      @Named("io") ExecutionThread backgroundExecutionThread,
                                      @Named("ui") ExecutionThread uiExecutionThread,
                                      @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchAccountsApiResponse> buildUseCaseObservable(final BankParameters bankParameters) {
        if(bankParameters.getShouldClearCache()){
            mAccountDatasource.clearAchAccountsCache();
        }

        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<AchAccountsApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<AchAccountsApiResponse>> apply(Session session) throws Exception {
                        bankParameters.addHeader(Header.TOKEN, session.getToken());
                        AchAccountParameters achAccountParameters = AchAccountParameters.create().withMemberId(Long.toString(session.getMemberId()));
                        achAccountParameters.addHeader(Header.TOKEN, session.getToken());

                        return mAccountDatasource.getMemberAchAccounts(achAccountParameters);
                    }
                })
                .flatMapMaybe(new Function<Optional<AchAccountsApiResponse>, MaybeSource<AchAccountsApiResponse>>() {
                    @Override
                    public MaybeSource<AchAccountsApiResponse> apply(Optional<AchAccountsApiResponse> achAccountsApiResponseOptional) throws Exception {
                        if (achAccountsApiResponseOptional.isPresent()) {
                            return Maybe.just(achAccountsApiResponseOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                })
                .toSingle()
                .flatMap(new Function<AchAccountsApiResponse, SingleSource<? extends List<AchAccount>>>() {
                    @Override
                    public SingleSource<? extends List<AchAccount>> apply(AchAccountsApiResponse achAccountsApiResponse) throws Exception {
                        return Observable.fromIterable(achAccountsApiResponse.getAchAccounts())
                                .flatMapMaybe(new Function<AchAccount, Maybe<? extends AchAccount>>() {
                                    @Override
                                    public Maybe<? extends AchAccount> apply(final AchAccount achAccount) throws Exception {
                                        return mAuthenticationDatasource.getSession()
                                                .flatMap(new Function<Session, SingleSource<? extends Bank>>() {
                                                    @Override
                                                    public SingleSource<? extends Bank> apply(Session session) throws Exception {
                                                        BankParameters bankParameters = new BankParameters(new HashMap<String, String>(), achAccount.getBankId());
                                                        bankParameters.addHeader(Header.TOKEN, session.getToken());

                                                        return mAccountDatasource.getBank(bankParameters)
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
                                                                .toSingle()
                                                                .onErrorReturn(new Function<Throwable, Bank>() {
                                                                    @Override
                                                                    public Bank apply(Throwable throwable) throws Exception {
                                                                        return Bank.create(Bank.INVALID_ID, "", BankType.ACH_BANK);
                                                                    }
                                                                });
                                                    }
                                                })
                                                .flatMapMaybe(new Function<Bank, Maybe<? extends AchAccount>>() {
                                                    @Override
                                                    public Maybe<? extends AchAccount> apply(Bank bank) throws Exception {
                                                        if (!bank.getId().equals(Bank.INVALID_ID)) {
                                                            return Maybe.just(achAccount.withBank(bank));
                                                        }
                                                        return Maybe.empty();
                                                    }
                                                });
                                    }
                                })
                                .toList();
                    }
                })
                .flatMap(new Function<List<AchAccount>, SingleSource<? extends AchAccountsApiResponse>>() {
                    @Override
                    public SingleSource<? extends AchAccountsApiResponse> apply(List<AchAccount> achAccounts) throws Exception {
                        return Single.just(AchAccountsApiResponse.create(achAccounts));
                    }
                });
    }
}
