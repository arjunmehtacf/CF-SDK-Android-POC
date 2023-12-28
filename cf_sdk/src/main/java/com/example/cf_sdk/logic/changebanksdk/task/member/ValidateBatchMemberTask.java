package com.example.cf_sdk.logic.changebanksdk.task.member;

import com.example.cf_sdk.logic.changebanksdk.CompletableUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.CreateBatchListMemberParameters;
import com.example.cf_sdk.logic.changebanksdk.source.MemberDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;

/**
 *
 * Creates new batch list member.
 */

public class ValidateBatchMemberTask extends CompletableUseCase<CreateBatchListMemberParameters> {
    private final MemberDatasource mMemberRepository;

    @Inject
    public ValidateBatchMemberTask(
            @Named("repository") MemberDatasource memberRepository,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);

        mMemberRepository = memberRepository;
    }

    @Override
    protected Completable buildUseCaseObservable(final CreateBatchListMemberParameters parameters) {
        return mMemberRepository.batchListConfirm(parameters);
    }
}
