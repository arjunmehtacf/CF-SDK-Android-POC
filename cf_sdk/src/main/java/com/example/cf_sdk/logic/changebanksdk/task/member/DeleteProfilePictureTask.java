package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.CompletableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.ProfilePictureParameters;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.functions.Function;

/**
 *
 * Use case for delete profile picture.
 */

public class DeleteProfilePictureTask extends CompletableUseCase<ProfilePictureParameters> {

    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public DeleteProfilePictureTask(
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
    protected Completable buildUseCaseObservable(final ProfilePictureParameters documentParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMapCompletable(new Function<Session, Completable>() {
                    @Override
                    public Completable apply(Session session) throws Exception {
                        documentParameters.addToken(session.getTokenType()+" "+session.getToken());
                        return mMemberRepository.deleteImageDocument(documentParameters);
                    }
                });
    }
}
