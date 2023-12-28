package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.DocumentApiResponse;
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
 * Use case for retrieving document by id.
 */

public class GetDocumentByIdTask extends SingleUseCase<DocumentParameters, DocumentApiResponse> {
    private final AuthenticationDatasource mAuthenticationDatasource;
    private final MemberDatasource mMemberRepository;

    @Inject
    public GetDocumentByIdTask(
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
    protected Single<DocumentApiResponse> buildUseCaseObservable(final DocumentParameters documentParameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends DocumentApiResponse>>() {
                    @Override
                    public SingleSource<? extends DocumentApiResponse> apply(Session session) throws Exception {
                        documentParameters.addToken(session.getToken());
                        documentParameters.setMemberId(session.getMemberId());
                        return mMemberRepository.getDocumentById(documentParameters)
                                .flatMapMaybe(new Function<Optional<DocumentApiResponse>, MaybeSource<DocumentApiResponse>>() {
                                    @Override
                                    public MaybeSource<DocumentApiResponse> apply(Optional<DocumentApiResponse> documentApiResponseOptional) throws Exception {
                                        if (documentApiResponseOptional.isPresent()) {
                                            return Maybe.just(documentApiResponseOptional.get());
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
