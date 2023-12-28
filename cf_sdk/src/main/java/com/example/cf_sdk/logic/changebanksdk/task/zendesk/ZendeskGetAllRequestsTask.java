package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for getting all requests for current user.
 */

public class ZendeskGetAllRequestsTask extends SingleUseCase<Parameters, List<RequestMapped>> {
    private ZendeskDatasource mZendeskDatasource;


    @Inject
    public ZendeskGetAllRequestsTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<List<RequestMapped>> buildUseCaseObservable(Parameters parameters) {
        return mZendeskDatasource.getAllZendeskRequests();
    }


}
