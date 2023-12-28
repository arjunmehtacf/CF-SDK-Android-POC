package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AgreementParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for fetching agreements.
 */

public class AgreementsTask extends SingleUseCase<AgreementParameters, File> {

    private MemberDatasource mMemberRepository;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public AgreementsTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {

        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mMemberRepository = memberRepository;
        mAuthenticationDatasource = authenticationDatasource;
    }

    @Override
    protected Single<File> buildUseCaseObservable(AgreementParameters agreementParameters) {
        return mAuthenticationDatasource.getSession().flatMap(new Function<Session, SingleSource<? extends File>>() {
            @Override
            public SingleSource<? extends File> apply(Session session) throws Exception {
                agreementParameters.addToken(session.getTokenType()+" "+session.getToken());
                agreementParameters.addHeader("Accept","application/pdf");
                return mMemberRepository.getAgreement(agreementParameters);
            }
        });
    }
}
