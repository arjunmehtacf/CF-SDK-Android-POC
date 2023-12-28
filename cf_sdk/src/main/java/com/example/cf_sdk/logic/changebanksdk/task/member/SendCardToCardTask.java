package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.CardToCardResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
* Use case for card to card transfer data.
*/

public class SendCardToCardTask extends SingleUseCase<CardToCardTransferParameters, CardToCardResponse> {
    private final AccountDatasource mAccountDataSource;
    private final AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public SendCardToCardTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") AccountDatasource accountDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mAccountDataSource = accountDatasource;
    }

    @Override
    protected Single<CardToCardResponse> buildUseCaseObservable(CardToCardTransferParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends CardToCardResponse>>() {
                    @Override
                    public SingleSource<? extends CardToCardResponse> apply(Session session) throws Exception {
                        stringParameters.addHeader(Header.TOKEN, session.getTokenType()+ " "+ session.getToken());
                        return mAccountDataSource.cardToCardTransfer(stringParameters);
                    }
                });
    }
}