package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchHistoryParameters;
import com.example.cf_sdk.logic.changebanksdk.response.AchHistoryResponse;
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
 * Task to get ach History for a member.
 */

public class GetAchHistoryTask extends SingleUseCase<AchHistoryParameters, AchHistoryResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetAchHistoryTask(@Named("repository") AccountDatasource accountDatasource,
                             @Named("repository") AuthenticationDatasource authenticationDatasource,
                             @Named("io") ExecutionThread backgroundExecutionThread,
                             @Named("ui") ExecutionThread uiExecutionThread,
                             @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<AchHistoryResponse> buildUseCaseObservable(final AchHistoryParameters parameters) {
        return mAuthenticationDatasource.getSession().flatMap(new Function<Session, SingleSource<? extends AchHistoryResponse>>() {
            @Override
            public SingleSource<? extends AchHistoryResponse> apply(Session session) throws Exception {
                parameters.setMemberId(session.getAccountNumber());
                parameters.addToken(session.getTokenType() + " " + session.getToken());
                return mAccountDatasource.getMemberAchHistory(parameters);
            }
        });

        /*return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends AchHistoryResponse>>() {
                    @Override
                    public SingleSource<? extends AchHistoryResponse> apply(Session session) throws Exception {
                        parameters.setMemberId(session.getAccountNumber());
                        parameters.addToken(session.getTokenType() + " " + session.getToken());
                        StringParameters stringParameters = StringParameters.create(Long.toString(session.getMemberId()));
                        stringParameters.addHeader(Header.TOKEN, session.getToken());
                        AchAccountParameters achAccountParameters = AchAccountParameters.create().withMemberId(Long.toString(session.getMemberId()));
                        achAccountParameters.addHeader(Header.TOKEN, session.getToken());

                        Single<AccountsApiResponse> accountsApiResponseSingle = mAccountDatasource
                                .getMemberAccounts(stringParameters)
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

                        Single<AchAccountsApiResponse> achAccountsApiResponseSingle = mAccountDatasource
                                .getMemberAchAccounts(achAccountParameters)
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
                                .toSingle();



                        return Single.zip(
                                mAccountDatasource.getMemberAchHistory(parameters),
                                accountsApiResponseSingle,
                                null,
                                new Function3<AchHistoryResponse, AccountsApiResponse, AchAccountsApiResponse, AchHistoryResponse>() {
                                    @Override
                                    public AchHistoryResponse apply(AchHistoryResponse achHistoryResponse, AccountsApiResponse accountsApiResponse, AchAccountsApiResponse achAccountsApiResponse) throws Exception {
                                        List<AchTransfer> achTransferHistoryWithAccountNumber = new ArrayList<>();
                                        for (AchTransfer achTransfer : achHistoryResponse.getAchTransferHistory()) {
                                            for (Account account : accountsApiResponse.getAccounts()) {
                                                if (account.getId().equals(achTransfer.getAccountId())) {
                                                    achTransfer = achTransfer.withAccountNumber(account.getLast4AccountNumber());
                                                }
                                            }

                                            for (AchAccount achAccount : achAccountsApiResponse.getAchAccounts()) {
                                                if (achAccount.getId().equals(achTransfer.getAchAccountId())) {
                                                    achTransfer = achTransfer.withAchAccountNumber(achAccount.getAccountNumber());
                                                }
                                            }

                                            achTransferHistoryWithAccountNumber.add(achTransfer);
                                        }


                                        return new AchHistoryResponse(achTransferHistoryWithAccountNumber);
                                    }
                                });
                    }
                });*/
    }
}
