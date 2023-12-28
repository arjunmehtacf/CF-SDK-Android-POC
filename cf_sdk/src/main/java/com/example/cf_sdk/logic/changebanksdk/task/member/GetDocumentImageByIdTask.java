package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.FileResponse;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.DocumentParameters;
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
 * Use case for retrieving image document by id.
 */

public class GetDocumentImageByIdTask extends SingleUseCase<DocumentParameters, FileResponse> {

    private AuthenticationDatasource mAuthenticationDatasource;
    private MemberDatasource mMemberRepository;

    @Inject
    public GetDocumentImageByIdTask(
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
    protected Single<FileResponse> buildUseCaseObservable(final DocumentParameters documentParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends FileResponse>>() {
                    @Override
                    public SingleSource<? extends FileResponse> apply(Session session) throws Exception {
                        documentParameters.addToken(session.getToken());
                        documentParameters.setMemberId(session.getMemberId());
                        return mMemberRepository.getDocumentImageById(documentParameters)
                                .flatMapMaybe(new Function<Optional<FileResponse>, MaybeSource<FileResponse>>() {
                                    @Override
                                    public MaybeSource<FileResponse> apply(Optional<FileResponse> fileResponseOptional) throws Exception {
                                        if (fileResponseOptional.isPresent()) {
                                            return Maybe.just(fileResponseOptional.get());
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
