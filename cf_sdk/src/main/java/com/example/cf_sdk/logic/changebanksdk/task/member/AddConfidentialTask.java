package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.oow.OowQuestions;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddConfidentialParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for adding user confidential information.
 */

public class AddConfidentialTask extends SingleUseCase<AddConfidentialParameters, OowQuestions> {

    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public AddConfidentialTask(
            @Named("repository")AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAuthenticationDatasource = authenticationDatasource;
        mMemberRepository = memberRepository;
    }

    @Override
    protected Single<OowQuestions> buildUseCaseObservable(final AddConfidentialParameters acp) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends OowQuestions>>() {
                    @Override
                    public SingleSource<? extends OowQuestions> apply(Session session) throws Exception {
                        acp.addToken(session.getToken());
                        acp.setMemberId(session.getMemberId());
                        return mMemberRepository.addConfidential(acp)
                                .flatMapMaybe(new Function<Optional<OowQuestions>, MaybeSource<OowQuestions>>() {
                                    @Override
                                    public MaybeSource<OowQuestions> apply(Optional<OowQuestions> oowQuestionsOptional) throws Exception {
                                        if (oowQuestionsOptional.isPresent()) {
                                            return Maybe.just(oowQuestionsOptional.get());
                                        } else {
                                            return Maybe.empty();
                                        }
                                    }
                                })
                                .toSingle();
                    }
                });
    }
}
