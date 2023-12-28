package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.UploadDocumentsParameters;
import com.example.cf_sdk.logic.changebanksdk.response.DocumentUploadResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Upload document task with single use case.
 */
public class UploadDocumentTask extends SingleUseCase<UploadDocumentsParameters, DocumentUploadResponse> {
    private MemberDatasource mMemberDataSource;
    private AuthenticationDatasource mAuthenticationDatasource;

    @Inject
    public UploadDocumentTask(@Named("repository") AuthenticationDatasource authenticationDatasource,
                              @Named("repository") MemberDatasource accountDatasource,
                              @Named("io") ExecutionThread backgroundExecutionThread,
                              @Named("ui") ExecutionThread uiExecutionThread,
                              @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mAuthenticationDatasource = authenticationDatasource;
        mMemberDataSource = accountDatasource;
    }

    @Override
    protected Single<DocumentUploadResponse> buildUseCaseObservable(final UploadDocumentsParameters parameters) {
        return mAuthenticationDatasource.getSession()
                .flatMap(new Function<Session, SingleSource<? extends DocumentUploadResponse>>() {
                    @Override
                    public SingleSource<? extends DocumentUploadResponse> apply(Session session) throws Exception {
                        parameters.addHeader(Header.TOKEN, session.getToken());
                        parameters.setMemberId(session.getMemberId());
                        parameters.ScanRefId(parameters.getmQuiryId());
                        return mMemberDataSource.uploadDocuments(parameters);
                    }
                });
    }
}
