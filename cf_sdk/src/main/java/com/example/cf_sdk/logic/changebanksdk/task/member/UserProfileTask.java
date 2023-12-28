package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.UserProfileResponse;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UserProfileParameter;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * User profile data task with single use case
 */
public class UserProfileTask extends SingleUseCase<UserProfileParameter,UserProfileResponse> {
    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public UserProfileTask(
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
    protected Single<UserProfileResponse> buildUseCaseObservable(final UserProfileParameter parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, Single<UserProfileResponse>>() {
                    @Override
                    public Single<UserProfileResponse> apply(Session session) throws Exception {
                        parameters.addToken(session.getTokenType() + " " + session.getToken());
                        return mMemberRepository.getUserProfile(parameters);
                    }
                });
    }
}