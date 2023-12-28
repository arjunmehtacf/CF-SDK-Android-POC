package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ResetPasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.net.HttpURLConnection;
import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 *
 * Use case for reset password.
 */

public class ResetPasswordTask extends SingleUseCase<ResetPasswordParameters, ChangebankResponse> {

    private final AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public ResetPasswordTask(
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
    protected Single<ChangebankResponse> buildUseCaseObservable(final ResetPasswordParameters resetPasswordParameters) {
        return mMemberRepository.resetPassword(resetPasswordParameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends ChangebankResponse>>() {
            @Override
            public SingleSource<? extends ChangebankResponse> apply(Throwable e) throws Exception {
                if (e instanceof NoSuchElementException) {
                    ChangebankResponse error = new ChangebankResponse();
                    error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                    error.setMessage("No Content");
                    if(resetPasswordParameters.isIsrememberusername())
                    {
                        mAuthenticationDatasource.saveUsername(resetPasswordParameters.getmUsername());
                    }
                    return Single.error(error);
                }
                return Single.error(e);
            }
        });

        /* mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Session session) throws Exception {
                        return mMemberRepository.resetPassword(
                                resetPasswordParameters.withTokenAndMemberId(session.getToken(),
                                        String.valueOf(session.getMemberId())));
                    }
                })
                .doOnSuccess(new Consumer<ChangebankResponse>() {
                    @Override
                    public void accept(ChangebankResponse response) throws Exception {
                        mAuthenticationDatasource.clearPinAndFingerprint();
                    }
                });*/
    }
}
