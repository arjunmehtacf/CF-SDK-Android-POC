package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestUpdatesMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for getting request updates for device.
 */

public class ZendeskGetRequestUpdatesTask extends SingleUseCase<Parameters, RequestUpdatesMapped> {
    private ZendeskDatasource mZendeskDatasource;


    @Inject
    public ZendeskGetRequestUpdatesTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<RequestUpdatesMapped> buildUseCaseObservable(Parameters parameters) {
        return mZendeskDatasource.getUpdatesForDevice();
    }


}
