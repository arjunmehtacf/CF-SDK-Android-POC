package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AppPreferenceDatasource;
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
 * Creates new batch list member.
 */

public class CreateBatchMemberTask extends SingleUseCase<CreateBatchListMemberParameters, Session> {
    private final AuthenticationDatasource mAuthenticationRepository;
    private final MemberDatasource mMemberRepository;
    private final AppPreferenceDatasource mAppPreferenceRepository;

    @Inject
    public CreateBatchMemberTask(
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
    protected Single<Session> buildUseCaseObservable(final CreateBatchListMemberParameters parameters) {
        return mMemberRepository.batchListCreateMember(parameters)
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
