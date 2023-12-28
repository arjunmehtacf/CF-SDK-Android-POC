package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.model.zendesk.ArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.CategoryMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestUpdatesMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SearchArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SectionMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.ZendeskFaqSearchParameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.google.common.base.Optional;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by gunveernatt on 5/29/18.
 */

public class ZendeskRepository implements ZendeskDatasource {

    private final ZendeskDatasource mZendeskRemoteDatasource;
    private final ZendeskDatasource mZendeskCacheDatasource;

    @Inject
    public ZendeskRepository(@Named("remote") ZendeskDatasource remote,
                             @Named("cache") ZendeskDatasource cache) {
        mZendeskRemoteDatasource = remote;
        mZendeskCacheDatasource = cache;
    }

    @Override
    public Single<Optional<List<CategoryMapped>>> getCategories() {


        Single<Optional<List<CategoryMapped>>> cache = mZendeskCacheDatasource.getCategories();

        Single<Optional<List<CategoryMapped>>> remote = mZendeskRemoteDatasource.getCategories()
                .flatMap(new Function<Optional<List<CategoryMapped>>, SingleSource<? extends Optional<List<CategoryMapped>>>>() {
                    @Override
                    public SingleSource<? extends Optional<List<CategoryMapped>>> apply(Optional<List<CategoryMapped>> categoryMappedList) throws Exception {
                        saveCategoriesInCache(categoryMappedList.get());
                        return Single.just(categoryMappedList);
                    }
                });

        return Single.concat(cache, remote)
                .filter(new Predicate<Optional<List<CategoryMapped>>>() {
                    @Override
                    public boolean test(Optional<List<CategoryMapped>> categoryMappedList) throws Exception {
                        return categoryMappedList.isPresent();
                    }
                }).firstOrError();
    }

    @Override
    public Single<Optional<List<SectionMapped>>> getSections(final CategoryMapped categoryMapped) {
        Single<Optional<List<SectionMapped>>> cache = mZendeskCacheDatasource.getSections(categoryMapped);

        Single<Optional<List<SectionMapped>>> remote = mZendeskRemoteDatasource.getSections(categoryMapped)
                .flatMap(new Function<Optional<List<SectionMapped>>, SingleSource<? extends Optional<List<SectionMapped>>>>() {
                    @Override
                    public SingleSource<? extends Optional<List<SectionMapped>>> apply(Optional<List<SectionMapped>> listOptional) throws Exception {
                        saveSectionsInCache(categoryMapped.getId(), listOptional.get());
                        return Single.just(listOptional);
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<List<SectionMapped>>>() {
                    @Override
                    public boolean test(Optional<List<SectionMapped>> sectionMappedList) throws Exception {
                        return sectionMappedList.isPresent();
                    }
                }).firstOrError();
    }

    @Override
    public Single<Optional<List<ArticleMapped>>> getArticles(final SectionMapped sectionMapped) {
        Single<Optional<List<ArticleMapped>>> cache = mZendeskCacheDatasource.getArticles(sectionMapped);

        Single<Optional<List<ArticleMapped>>> remote = mZendeskRemoteDatasource.getArticles(sectionMapped)
                .flatMap(new Function<Optional<List<ArticleMapped>>, SingleSource<? extends Optional<List<ArticleMapped>>>>() {
                    @Override
                    public SingleSource<? extends Optional<List<ArticleMapped>>> apply(Optional<List<ArticleMapped>> articleMappeds) throws Exception {
                        saveArticlesInCache(sectionMapped.getId(), articleMappeds.get());
                        return Single.just(articleMappeds);
                    }
                });

        return Single
                .concat(cache, remote)
                .filter(new Predicate<Optional<List<ArticleMapped>>>() {
                    @Override
                    public boolean test(Optional<List<ArticleMapped>> articleMappeds) throws Exception {
                        return articleMappeds.isPresent();
                    }
                }).firstOrError();
    }

    @Override
    public Single<List<SearchArticleMapped>> searchZendeskArticles(ZendeskFaqSearchParameters zendeskFaqSearchParameters) {
        return mZendeskRemoteDatasource.searchZendeskArticles(zendeskFaqSearchParameters);
    }

    @Override
    public Single<List<RequestMapped>> getAllZendeskRequests() {
        return Single.zip(
                mZendeskRemoteDatasource.getAllZendeskRequests(),
                getUpdatesForDevice(), new BiFunction<List<RequestMapped>, RequestUpdatesMapped, List<RequestMapped>>() {
                    @Override
                    public List<RequestMapped> apply(List<RequestMapped> requestMappedList, RequestUpdatesMapped requestUpdatesMapped) throws Exception {
                        if (requestUpdatesMapped != null) {
                            for (RequestMapped requestMapped : requestMappedList) {
                                if (requestUpdatesMapped.isRequestUnread(requestMapped.getId())) {
                                    requestMapped.setHasUnreadMessages();
                                }
                            }
                        }
                        return requestMappedList;
                    }
                });
    }

    @Override
    public Single<RequestUpdatesMapped> getUpdatesForDevice() {
        return mZendeskRemoteDatasource.getUpdatesForDevice();
    }

    @Override
    public void saveCategoriesInCache(List<CategoryMapped> categoryMappedList) {
        mZendeskCacheDatasource.saveCategoriesInCache(categoryMappedList);
    }

    @Override
    public void saveSectionsInCache(Long categoryId, List<SectionMapped> sectionMappeds) {
        mZendeskCacheDatasource.saveSectionsInCache(categoryId, sectionMappeds);
    }

    @Override
    public void saveArticlesInCache(Long sectionId, List<ArticleMapped> articleMappeds) {
        mZendeskCacheDatasource.saveArticlesInCache(sectionId, articleMappeds);
    }

    @Override
    public void clearZendeskCache() {
        mZendeskCacheDatasource.clearZendeskCache();
    }
}
