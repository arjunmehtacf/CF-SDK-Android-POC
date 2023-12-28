package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Account;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.GetAccountParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AccountsApiResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

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
 * Task to get member accounts with the latest balance.
 */

public class GetAccountsWithUpdatedBalanceTask extends SingleUseCase<Parameters, AccountsApiResponse> {

    private AccountDatasource mAccountRepository;
    private AuthenticationDatasource mAuthenticationRepository;

    private Account tempAccount;

    private Session tempSession;
    @Inject
    public GetAccountsWithUpdatedBalanceTask(
            @Named("repository") AccountDatasource accountRepository,
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationRepository = authenticationRepository;
        mAccountRepository = accountRepository;
    }

    @Override
    protected Single<AccountsApiResponse> buildUseCaseObservable(Parameters parameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Optional<AccountsApiResponse>>>() {
                    @Override
                    public SingleSource<? extends Optional<AccountsApiResponse>> apply(Session session) throws Exception {
                        StringParameters stringParameters = StringParameters.create(session.getCustomerNumber());
                        stringParameters.addHeader(Header.TOKEN, session.getTokenType()+" "+session.getToken());
                        return mAccountRepository.getMemberAccounts(stringParameters);
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
                .toSingle()
                .flatMap(new Function<AccountsApiResponse, SingleSource<? extends List<Account>>>() {
                    @Override
                    public SingleSource<? extends List<Account>> apply(AccountsApiResponse accountsApiResponse) throws Exception {
                        return Observable.fromIterable(accountsApiResponse.getAccounts())
                                .flatMapSingle(new Function<Account, SingleSource<Account>>() {
                                    @Override
                                    public SingleSource<Account> apply(final Account account) throws Exception {
                                        return mAuthenticationRepository.getSession()
                                                .flatMap(new Function<Session, Single<Optional<Account>>>() {
                                                    @Override
                                                    public Single<Optional<Account>> apply(Session session) throws Exception {
                                                        tempAccount = account;
                                                        tempSession = session;
                                                        GetAccountParameters getAccountParameters = GetAccountParameters.createById(account.getCardsList().get(0).getAccountNumber());
                                                        getAccountParameters.setFromRemoteOnly(true);
                                                        getAccountParameters.addToken(session.getTokenType()+" "+session.getToken());
                                                        session.setAccountNumber(account.getCardsList().get(0).getAccountNumber());
                                                        session.setProgramId(account.getProgramID());
                                                        session.setAllowTransfer(account.isallowTransfer());

                                                        return mAccountRepository.getAccountById(getAccountParameters)
                                                                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends Optional<Account>>>() {
                                                                    @Override
                                                                    public SingleSource<? extends Optional<Account>> apply(Throwable throwable) throws Exception {
                                                                        return Single.just(Optional.of(account));
                                                                    }
                                                                });

                                                    }
                                                })
                                                .flatMap(new Function<Optional<Account>, SingleSource<? extends Account>>() {
                                                    @Override
                                                    public SingleSource<? extends Account> apply(Optional<Account> accountOptional) throws Exception {
                                                        tempAccount.setAccountCurrencyCode(accountOptional.get().getAccountCurrencyCode());
                                                        tempAccount.setBalance(accountOptional.get().getBalance());
                                                        mAccountRepository.saveAccount(tempAccount);
                                                        return Single.just(tempAccount);
                                                    }
                                                });
                                    }
                                })
                                .toList();
                    }
                })
                .flatMap(new Function<List<Account>, SingleSource<? extends AccountsApiResponse>>() {
                    @Override
                    public SingleSource<? extends AccountsApiResponse> apply(List<Account> accounts) throws Exception {
                        AccountsApiResponse accountsApiResponse = AccountsApiResponse.create(accounts);
                        tempSession.setAccountNumber(accountsApiResponse.getAccounts().get(0).getCardsList().get(0).getAccountNumber());
                        return Single.just(accountsApiResponse);
                    }
                });
    }
}
