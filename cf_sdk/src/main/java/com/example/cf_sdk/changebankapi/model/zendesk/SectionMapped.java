package com.example.cf_sdk.changebankapi.model.zendesk;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gunveernatt on 5/30/18.
 */

public class SectionMapped implements Serializable {

    private Long mId=0L;

    private String mUrl = "";

    private String mHtmlUrl = "";

    private Long mCategoryId = 0L;

    private int mPosition=0;

    private String mSorting = "";

    private Date mCreatedAt;

    private Date mUpdatedAt;

    private String mName = "";

    private String mDescription = "";

    private String mLocale = "";

    private String mSourceLocale = "";

    private boolean mIsOutdated=false;

    private int mArticleCount=0;


    public SectionMapped(
            Long id,
            String url,
            String htmlUrl,
            Long categoryId,
            int position,
            String sorting,
            Date createdDate,
            Date updatedDate,
            String name,
            String description,
            String locale,
            String sourceLocale,
            boolean isOutdated,
            int articleCount) {

        mId = id;
        mUrl = url;
        mHtmlUrl = htmlUrl;
        mCategoryId = categoryId;
        mPosition = position;
        mSorting = sorting;
        mCreatedAt = createdDate;
        mUpdatedAt = updatedDate;
        mName = name;
        mDescription = description;
        mLocale = locale;
        mSourceLocale = sourceLocale;
        mIsOutdated = isOutdated;
        mArticleCount = articleCount;
    }


    /**
     * Gets the ID of the category
     *
     * @return the ID of the category
     */
    @Nullable
    public Long getId() {
        return mId;
    }

    /**
     * Gets the API URL of the category
     *
     * @return the API URL of the category
     */
    @Nullable
    public String getUrl() {
        return mUrl;
    }

    /**
     * Gets the URL of the category that can be opened in a web browser
     *
     * @return the URL of the category that can be opened in a web browser
     */
    @Nullable
    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    /**
     * Gets the id of the Category that this section belongs to
     *
     * @return the id of the Category that this section belongs to
     */
    @Nullable
    public Long getCategoryId() {
        return mCategoryId;
    }

    /**
     * Gets the position of the category. This is applicable if the categories are manually sorted.
     *
     * @return the position of the category
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * Gets the sorting of the section, e.g. 'manual'
     *
     * @return the sorting of the section
     */
    @Nullable
    public String getSorting() {
        return mSorting;
    }

    /**
     * Gets the date that the category was created at.
     *
     * @return the date that the category was created at.
     */
    @Nullable
    public Date getCreatedAt() {
        return mCreatedAt;
    }

    /**
     * Gets the date that the category was updated at.
     *
     * @return the date that the category was updated at.
     */
    @Nullable
    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    /**
     * Gets the name of the category
     *
     * @return the name of the category
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Gets the description of the category
     *
     * @return the description of the category
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    /**
     * Gets the locale of the category in the form of ll-CC, e.g. en-US
     *
     * @return the locale of the category in the form of ll-CC, e.g. en-US
     */
    @Nullable
    public String getLocale() {
        return mLocale;
    }

    /**
     * Gets the source locale of the category in the form of ll-CC, e.g. en-US
     *
     * @return the source locale of the category in the form of ll-CC, e.g. en-US
     */
    @Nullable
    public String getSourceLocale() {
        return mSourceLocale;
    }

    /**
     * Checks whether the category is outdated or not
     *
     * @return true if the category is outdated, false otherwise
     */
    public boolean isOutdated() {
        return mIsOutdated;
    }

    /**
     * Gets the number of articles in this section
     *
     * @return the number of articles in this section
     */
    public int getArticlesCount() {
        return mArticleCount;
    }
}
