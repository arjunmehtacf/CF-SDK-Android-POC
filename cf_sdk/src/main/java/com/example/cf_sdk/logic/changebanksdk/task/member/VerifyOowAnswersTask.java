package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.VerifyOutOfWalletParameters;
import com.example.cf_sdk.logic.changebanksdk.response.VerifyOowResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Task for oow answer verification.
 */

public class VerifyOowAnswersTask extends SingleUseCase<VerifyOutOfWalletParameters, VerifyOowResponse> {
    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public VerifyOowAnswersTask(
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
    protected Single<VerifyOowResponse> buildUseCaseObservable(final VerifyOutOfWalletParameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends VerifyOowResponse>>() {
                    @Override
                    public SingleSource<? extends VerifyOowResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getToken());
                        parameters.setMemberId(session.getMemberId());
                        return mMemberRepository.verifyOowQuestion(parameters);
                    }
                });
    }
}
