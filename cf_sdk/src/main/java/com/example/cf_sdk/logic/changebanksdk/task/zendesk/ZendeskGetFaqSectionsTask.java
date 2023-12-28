package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SectionMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.SectionsForCategoryParameters;
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
 * Use case for getting help center Sections
 */

public class ZendeskGetFaqSectionsTask extends SingleUseCase<SectionsForCategoryParameters, List<SectionMapped>> {
    private ZendeskDatasource mZendeskDatasource;


    @Inject
    public ZendeskGetFaqSectionsTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<List<SectionMapped>> buildUseCaseObservable(SectionsForCategoryParameters sectionsForCategoryParameters) {
        return mZendeskDatasource.getSections(sectionsForCategoryParameters.getCategoryMapped())
                .flatMapMaybe(new Function<Optional<List<SectionMapped>>, MaybeSource<List<SectionMapped>>>() {
                    @Override
                    public MaybeSource<List<SectionMapped>> apply(Optional<List<SectionMapped>> listOptional) throws Exception {
                        if (listOptional.isPresent()) {
                            return Maybe.just(listOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                }).toSingle();
    }


}
