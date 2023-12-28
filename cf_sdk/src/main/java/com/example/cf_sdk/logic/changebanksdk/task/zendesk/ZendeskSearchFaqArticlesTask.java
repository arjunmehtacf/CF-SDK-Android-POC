package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SearchArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.ZendeskFaqSearchParameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

/**
 *
 * Use case for searching Articles.
 */

public class ZendeskSearchFaqArticlesTask extends SingleUseCase<ZendeskFaqSearchParameters, List<SearchArticleMapped>> {
    private ZendeskDatasource mZendeskDatasource;

    @Inject
    public ZendeskSearchFaqArticlesTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<List<SearchArticleMapped>> buildUseCaseObservable(ZendeskFaqSearchParameters zendeskFaqSearchParameters) {
        return mZendeskDatasource.searchZendeskArticles(zendeskFaqSearchParameters);
    }


}
