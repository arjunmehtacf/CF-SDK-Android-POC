package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.CategoryMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.ChangebankError;
import com.google.common.base.Optional;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 *
 * Use case for getting help center Categories
 */

public class ZendeskGetFaqCategoriesTask extends SingleUseCase<Parameters, List<CategoryMapped>> {
    private ZendeskDatasource mZendeskDatasource;


    @Inject
    public ZendeskGetFaqCategoriesTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<List<CategoryMapped>> buildUseCaseObservable(Parameters parameters) {
        return mZendeskDatasource.getCategories()
                .flatMapMaybe(new Function<Optional<List<CategoryMapped>>, MaybeSource<List<CategoryMapped>>>() {
                    @Override
                    public MaybeSource<List<CategoryMapped>> apply(Optional<List<CategoryMapped>> listOptional) throws Exception {
                        if (listOptional.isPresent()) {
                            return Maybe.just(listOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                }).toSingle();
    }


}
