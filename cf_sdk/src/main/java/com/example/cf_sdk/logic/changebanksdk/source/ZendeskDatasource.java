package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.model.zendesk.ArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.CategoryMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.RequestUpdatesMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SearchArticleMapped;
import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SectionMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.zendesk.ZendeskFaqSearchParameters;
import com.google.common.base.Optional;

import java.util.List;

import io.reactivex.Single;

/**
 * Zendesk data source.
 */

public interface ZendeskDatasource {

    Single<Optional<List<CategoryMapped>>> getCategories();

    Single<Optional<List<SectionMapped>>> getSections(CategoryMapped categoryMapped);

    Single<Optional<List<ArticleMapped>>> getArticles(SectionMapped sectionMapped);

    Single<List<SearchArticleMapped>> searchZendeskArticles(ZendeskFaqSearchParameters zendeskFaqSearchParameters);

    Single<List<RequestMapped>> getAllZendeskRequests();

    Single<RequestUpdatesMapped> getUpdatesForDevice();

    void saveCategoriesInCache(List<CategoryMapped> categoryMappedList);

    void saveSectionsInCache(Long categoryId, List<SectionMapped> sectionMappeds);

    void saveArticlesInCache(Long sectionId, List<ArticleMapped> articleMappeds);

    void clearZendeskCache();
}
