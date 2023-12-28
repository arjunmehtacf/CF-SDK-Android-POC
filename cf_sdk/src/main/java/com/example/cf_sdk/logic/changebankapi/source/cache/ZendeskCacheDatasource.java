package com.example.cf_sdk.logic.changebankapi.source.cache;

import com.example.cf_sdk.logic.changebanksdk.model.zendesk.ArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.CategoryMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestUpdatesMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SearchArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SectionMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.ZendeskFaqSearchParameters;
import com.example.cf_sdk.logic.changebanksdk.source.ZendeskDatasource;
import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;

public class ZendeskCacheDatasource implements ZendeskDatasource {


    private List<CategoryMapped> mCategoryMappedList;
    private HashMap<Long, List<SectionMapped>> mSectionMappedList;
    private HashMap<Long, List<ArticleMapped>> mArticleMappedList;

    public ZendeskCacheDatasource() {
        mSectionMappedList = new HashMap<>();
        mArticleMappedList = new HashMap<>();
    }

    @Override
    public Single<Optional<List<CategoryMapped>>> getCategories() {
        return Single.just(Optional.fromNullable(mCategoryMappedList));
    }

    @Override
    public Single<Optional<List<SectionMapped>>> getSections(CategoryMapped categoryMapped) {
        return Single.just(Optional.fromNullable(mSectionMappedList.get(categoryMapped.getId())));
    }

    @Override
    public Single<Optional<List<ArticleMapped>>> getArticles(SectionMapped sectionMapped) {
        return Single.just(Optional.fromNullable(mArticleMappedList.get(sectionMapped.getId())));
    }

    @Override
    public Single<List<SearchArticleMapped>> searchZendeskArticles(ZendeskFaqSearchParameters zendeskFaqSearchParameters) {
        throw new UnsupportedOperationException(
                "ZendeskCacheDatasource not support searchZendeskArticles");
    }

    @Override
    public Single<List<RequestMapped>> getAllZendeskRequests() {
        throw new UnsupportedOperationException(
                "ZendeskCacheDatasource not support getAllZendeskRequests");
    }


    @Override
    public Single<RequestUpdatesMapped> getUpdatesForDevice() {
        throw new UnsupportedOperationException(
                "ZendeskCacheDatasource not support getUpdatesForDevice");
    }

    @Override
    public void saveCategoriesInCache(List<CategoryMapped> categoryMappedList) {
        mCategoryMappedList = categoryMappedList;
    }

    @Override
    public void saveSectionsInCache(Long categoryId, List<SectionMapped> sectionMappedList) {
        mSectionMappedList.put(categoryId, sectionMappedList);
    }

    @Override
    public void saveArticlesInCache(Long sectionId, List<ArticleMapped> articleMappedList) {
        mArticleMappedList.put(sectionId, articleMappedList);
    }

    @Override
    public void clearZendeskCache() {
        mCategoryMappedList = null;
        mSectionMappedList = new HashMap<>();
        mArticleMappedList = new HashMap<>();
    }
}
