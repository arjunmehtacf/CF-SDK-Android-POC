package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import java.io.Serializable;

/**
 * Created by gunveernatt on 6/4/18.
 */

public class SearchArticleMapped implements Serializable{

    private final ArticleMapped mArticleMapped;
    private final SectionMapped mSectionMapped;
    private final CategoryMapped mCategoryMapped;

    /**
     * Wraps an {@link ArticleMapped} and also includes information of the location structure of the {@link ArticleMapped}.
     *
     * @param article  The original article that we are going to wrap
     * @param section  The section that this article belongs to
     * @param category The category that this article belongs to
     */
    public SearchArticleMapped(ArticleMapped article, SectionMapped section, CategoryMapped category) {
        mArticleMapped = article;
        mSectionMapped = section;
        mCategoryMapped = category;
    }

    /**
     * Gets the wrapped {@link ArticleMapped} object.
     *
     * @return the original {@link ArticleMapped} object
     */
    public ArticleMapped getArticle() {
        return mArticleMapped;
    }

    /**
     * Gets the section that this article belongs in.
     *
     * @return The section that the article belongs in or null if there was an issue in determining
     * this.
     */
    public SectionMapped getSection() {
        return mSectionMapped;
    }

    /**
     * Gets the category that this article belongs in.
     *
     * @return The category that this article belongs in or null if there was an issue in determining
     * this.
     */
    public CategoryMapped getCategory() {
        return mCategoryMapped;
    }
}
