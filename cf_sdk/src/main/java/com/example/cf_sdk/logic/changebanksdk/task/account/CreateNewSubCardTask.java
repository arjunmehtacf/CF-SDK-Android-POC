package com.example.cf_sdk.logic.changebanksdk.task.account;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.account.CreateNewSubCardResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CreateSubCardParameter;
import com.example.cf_sdk.logic.changebanksdk.source.AccountDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Use case for create new sub card.
 */
public class CreateNewSubCardTask extends SingleUseCase<CreateSubCardParameter, CreateNewSubCardResponse> {

    private AccountDatasource mAccountDatasource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public CreateNewSubCardTask(@Named("repository") AccountDatasource accountDatasource,
                               @Named("repository") AuthenticationDatasource authenticationDatasource,
                               @Named("io") ExecutionThread backgroundExecutionThread,
                               @Named("ui") ExecutionThread uiExecutionThread,
                               @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mAccountDatasource = accountDatasource;
    }

    @Override
    protected Single<CreateNewSubCardResponse> buildUseCaseObservable(final CreateSubCardParameter createSubCardParameter) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends CreateNewSubCardResponse>>() {
                    @Override
                    public SingleSource<? extends CreateNewSubCardResponse> apply(Session session) throws Exception {
                        createSubCardParameter.addToken(session.getTokenType() + " " + session.getToken());
                        return mAccountDatasource.createSubCard(createSubCardParameter);
                    }
                });
    }
}
