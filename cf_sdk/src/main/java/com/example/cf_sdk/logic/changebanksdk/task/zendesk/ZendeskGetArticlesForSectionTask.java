package com.example.cf_sdk.logic.changebanksdk.task.zendesk;

import com.example.cf_sdk.logic.changebanksdk.SingleUseCase;
import com.example.cf_sdk.logic.changebanksdk.executor.ExecutionThread;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.ArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.ArticlesForSectionParameters;
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
 * Use case for getting Articles for a particular section.
 */

public class ZendeskGetArticlesForSectionTask extends SingleUseCase<ArticlesForSectionParameters, List<ArticleMapped>> {
    private ZendeskDatasource mZendeskDatasource;

    @Inject
    public ZendeskGetArticlesForSectionTask(
            @Named("repository") ZendeskDatasource zendeskDatasource,
            @Named("io") ExecutionThread backgroundExecutionThread,
            @Named("ui") ExecutionThread uiExecutionThread,
            @Named("ErrorHandler") ChangebankError errorHandler) {
        super(backgroundExecutionThread, uiExecutionThread, errorHandler);
        mZendeskDatasource = zendeskDatasource;
    }

    protected Single<List<ArticleMapped>> buildUseCaseObservable(ArticlesForSectionParameters articlesForSectionParameters) {
        return mZendeskDatasource.getArticles(articlesForSectionParameters.getSectionMapped())
                .flatMapMaybe(new Function<Optional<List<ArticleMapped>>, MaybeSource<List<ArticleMapped>>>() {
                    @Override
                    public MaybeSource<List<ArticleMapped>> apply(Optional<List<ArticleMapped>> listOptional) throws Exception {
                        if (listOptional.isPresent()) {
                            return Maybe.just(listOptional.get());
                        } else {
                            return Maybe.empty();
                        }
                    }
                }).toSingle();
    }


}
