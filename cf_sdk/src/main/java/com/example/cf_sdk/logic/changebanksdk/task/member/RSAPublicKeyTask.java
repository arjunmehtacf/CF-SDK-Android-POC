package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.RSAPublicKeyResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UserProfileParameter;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Use case for get RSA Public key from server.
 */
public class RSAPublicKeyTask extends SingleUseCase<UserProfileParameter, RSAPublicKeyResponse> {
    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public RSAPublicKeyTask(
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
    protected Single<RSAPublicKeyResponse> buildUseCaseObservable(final UserProfileParameter parameters) {
        return mAuthenticationDatasource.getSession().flatMap(new Function<Session, SingleSource<? extends RSAPublicKeyResponse>>() {
            @Override
            public SingleSource<? extends RSAPublicKeyResponse> apply(Session session) throws Exception {
                parameters.addToken(session.getTokenType()+" "+session.getToken());
                return mMemberRepository.getRsaPublicKey(parameters);
            }
        });
    }
}
