package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
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
 * Creates new member.
 */

public class CreateMemberTask extends SingleUseCase<CreateMemberParameters, Session> {
    private final AuthenticationDatasource mAuthenticationRepository;
    private final MemberDatasource mMemberRepository;
    private final AppPreferenceDatasource mAppPreferenceRepository;

    @Inject
    public CreateMemberTask(
            @Named("repository") AppPreferenceDatasource appPreferenceRepository,
            @Named("repository") AuthenticationDatasource authenticationRepository,
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mAppPreferenceRepository = appPreferenceRepository;
        mAuthenticationRepository = authenticationRepository;
        mMemberRepository = memberRepository;
    }

    @Override
    protected Single<Session> buildUseCaseObservable(final CreateMemberParameters parameters) {
        CreateMemberParameters param = parameters;
        return mMemberRepository.createMember(parameters).onErrorResumeNext(new Function<Throwable, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(Throwable e) throws Exception {
                        if (e instanceof NoSuchElementException) {
                            ChangebankResponse error = new ChangebankResponse();
                            error.setHttpCode(HttpURLConnection.HTTP_NO_CONTENT);
                            error.setMessage("No Content");
                            if (param.isRememberUsername()) {
                                mAuthenticationRepository.saveUsername(param.getUsername());
                            }
                            return Single.error(error);
                        }
                        return Single.error(e);
                    }
                })
                .flatMap(new Function<Session, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(Session session) throws Exception {
                        mAuthenticationRepository.saveSession(session);
                        mAppPreferenceRepository.setHasLoggedInAlready();
                        if (parameters.isRememberUsername()) {
                            mAuthenticationRepository.saveUsername(parameters.getUsername());
                        }
                        return Single.just(session);
                    }
                });
    }
}
