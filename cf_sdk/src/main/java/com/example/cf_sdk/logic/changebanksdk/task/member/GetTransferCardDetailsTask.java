package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.CardToCardTransferParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
* Use case for transfer card details.
*/

public class GetTransferCardDetailsTask extends SingleUseCase<CardToCardTransferParameters,MemberDetails> {
    private final MemberDatasource mMemberDatasource;
    private final AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public GetTransferCardDetailsTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mMemberDatasource = memberDatasource;
    }

    @Override
    protected Single<MemberDetails> buildUseCaseObservable(final CardToCardTransferParameters stringParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends MemberDetails>>() {
                    @Override
                    public SingleSource<? extends MemberDetails> apply(Session session) throws Exception {
                        stringParameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mMemberDatasource.getCardToCardAccountDetails(stringParameters);
                    }
                });
    }
}
