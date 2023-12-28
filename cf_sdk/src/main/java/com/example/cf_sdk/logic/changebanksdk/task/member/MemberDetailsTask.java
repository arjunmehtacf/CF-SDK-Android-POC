package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.MemberDetails;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.MemberDetailParameters;
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
 * Get member Details.
 */

public class MemberDetailsTask extends SingleUseCase<MemberDetailParameters, MemberDetails> {
    private final MemberDatasource mMemberRepository;
    private final AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public MemberDetailsTask(
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
    protected Single<MemberDetails> buildUseCaseObservable(final MemberDetailParameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends MemberDetails>>() {
                    @Override
                    public SingleSource<? extends MemberDetails> apply(Session session) throws Exception {
                        parameters.setCustomerNumber(session.getCustomerNumber());
                        parameters.addToken(session.getTokenType()+" "+session.getToken());
                        return mMemberRepository.getMemberDetails(parameters)
                                .flatMapMaybe(new Function<Optional<MemberDetails>, MaybeSource<MemberDetails>>() {
                                    @Override
                                    public MaybeSource<MemberDetails> apply(Optional<MemberDetails> memberDetailsOptional) throws Exception {
                                        if (memberDetailsOptional.isPresent()) {
                                            return Maybe.just(memberDetailsOptional.get());
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
