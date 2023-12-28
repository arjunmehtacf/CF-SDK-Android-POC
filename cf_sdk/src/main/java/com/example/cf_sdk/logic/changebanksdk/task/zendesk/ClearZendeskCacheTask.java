package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for clearing zendesk cache.
 */

public class ClearZendeskCacheTask extends SingleUseCase<Parameters, ChangebankResponse> {
    private ZendeskDatasource mZendeskDatasource;


    @Inject
    public ClearZendeskCacheTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<ChangebankResponse> buildUseCaseObservable(Parameters parameters) {
        mZendeskDatasource.clearZendeskCache();
        return Single.just(new ChangebankResponse());
    }


}
