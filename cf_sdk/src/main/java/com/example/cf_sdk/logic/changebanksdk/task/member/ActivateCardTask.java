package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.ActivateCardResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ActivateCardParameter;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Use case for activate card API.
 */
public class ActivateCardTask extends SingleUseCase<ActivateCardParameter, ActivateCardResponse> {
    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public ActivateCardTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mMemberRepository = memberRepository;
    }


    @Override
    protected Single<ActivateCardResponse> buildUseCaseObservable(final ActivateCardParameter parameters) {
        return mAuthenticationDatasource.getSession().flatMap(new Function<Session, SingleSource<? extends ActivateCardResponse>>() {
            @Override
            public SingleSource<? extends ActivateCardResponse> apply(Session session) throws Exception {
                parameters.addToken(session.getTokenType()+" "+session.getToken());
                return mMemberRepository.getActivateCard(parameters);
            }
        });
    }
}
