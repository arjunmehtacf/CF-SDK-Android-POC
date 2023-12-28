package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SendPhoneCodeParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Use case to request a verification code for the phone number.
 */

public class SendPhoneCodeTask extends SingleUseCase<SendPhoneCodeParameters,ChangebankResponse> {
    private MemberDatasource mMemberRepository;
    private AuthenticationDatasource mAuthenticationDatasource;
    @Inject
    public SendPhoneCodeTask(
            @Named("repository") AuthenticationDatasource authenticationDatasource,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mMemberRepository = memberRepository;
        mAuthenticationDatasource =authenticationDatasource;
    }

    @Override
    protected Single<ChangebankResponse> buildUseCaseObservable(SendPhoneCodeParameters parameters) {

        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, Single<ChangebankResponse>>() {
                    @Override
                    public Single<ChangebankResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mMemberRepository.sendPhoneCode(parameters);
                    }
                });
    }
}
