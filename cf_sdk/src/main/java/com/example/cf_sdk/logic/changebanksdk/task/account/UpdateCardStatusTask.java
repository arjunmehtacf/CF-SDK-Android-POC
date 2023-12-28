package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.Card;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.UpdateCardStatusParameters;
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
 * Task to Update Card Status for a debit card.
 */

public class UpdateCardStatusTask extends SingleUseCase<UpdateCardStatusParameters, Card> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public UpdateCardStatusTask(@Named("repository") AccountDatasource accountDatasource,
                                @Named("repository") AuthenticationDatasource authenticationDatasource,
                                @Named("io") ExecutionThread backgroundExecutionThread,
                                @Named("ui") ExecutionThread uiExecutionThread,
                                @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<Card> buildUseCaseObservable(final UpdateCardStatusParameters updateCardStatusParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends Card>>() {
                    @Override
                    public SingleSource<? extends Card> apply(Session session) throws Exception {
                        updateCardStatusParameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mAccountDatasource.updateCardStatus(updateCardStatusParameters);
                    }
                });
    }
}
