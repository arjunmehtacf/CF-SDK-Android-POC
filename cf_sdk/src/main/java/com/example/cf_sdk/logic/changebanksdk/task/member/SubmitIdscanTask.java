package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.IdscanParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IdscanResponse;
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
 * Submits documents for user identity verification.
 */

public class SubmitIdscanTask extends SingleUseCase<IdscanParameters, IdscanResponse> {

    private AuthenticationDatasource mAuthenticationRepository;
    private MemberDatasource mMemberRepository;

    @Inject
    SubmitIdscanTask(@Named("repository") AuthenticationDatasource authenticationRepository,
                     @Named("repository") MemberDatasource memberRepository,
                     @Named("io") ExecutionThread backgroundExecutionThread,
                     @Named("ui") ExecutionThread uiExecutionThread,
                     @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationRepository = authenticationRepository;
        mMemberRepository = memberRepository;
    }

    @Override
    protected Single<IdscanResponse> buildUseCaseObservable(final IdscanParameters parameters) {
        return mAuthenticationRepository.getSession()
                .flatMap(new Function<Session, SingleSource<? extends IdscanResponse>>() {
                    @Override
                    public SingleSource<? extends IdscanResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getToken());
                        parameters.setMemberId(session.getMemberId());
                        return mMemberRepository.submitIdscan(parameters);
                    }
                });
    }
}
