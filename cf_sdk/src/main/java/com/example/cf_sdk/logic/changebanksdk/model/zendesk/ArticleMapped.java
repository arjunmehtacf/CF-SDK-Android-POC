package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by gunveernatt on 5/30/18.
 */

public class ArticleMapped implements Serializable{

    private Long mId;

    private String mUrl;

    private String mHtmlUrl;

    private Long mAuthorId;

    private boolean mIsCommentsDisabled;

    private List<String> mLabelNames;

    private boolean mIsDraft;

    private int mVoteSum;

    private int mVoteCount;

    private Long mSectionId;

    private Date mCreatedAt;

    private Date mUpdatedAt;

    private String mTitle;

    private String mBody;

    private String mSourceLocale;

    private String mLocale;

    private boolean mIsOutdated;

    private int mUpVoteCount;

    private int mDownVoteCount;

    public ArticleMapped(
            Long id,
            String url,
            String htmlUrl,
            Long authorId,
            boolean isCommentsDisabled,
            List<String> labelNames,
            boolean isDraft,
            int voteSum,
            int voteCount,
            Long sectionId,
            Date createdDate,
            Date updatedDate,
            String title,
            String body,
            String sourceLocale,
            String locale,
            boolean isOutdated,
            int upVoteCount,
            int downVoteCount) {

        mId = id;
        mUrl = url;
        mHtmlUrl = htmlUrl;
        mAuthorId = authorId;
        mIsCommentsDisabled = isCommentsDisabled;
        mLabelNames = labelNames;
        mIsDraft = isDraft;
        mVoteSum = voteSum;
        mVoteCount = voteCount;
        mSectionId = sectionId;
        mCreatedAt = createdDate;
        mUpdatedAt = updatedDate;
        mTitle = title;
        mBody = body;
        mLocale = locale;
        mSourceLocale = sourceLocale;
        mIsOutdated = isOutdated;
        mUpVoteCount = upVoteCount;
        mDownVoteCount = downVoteCount;
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
     * Gets the ID of the author of the article
     *
     * @return the ID of the author of the article
     * @since 1.0.0.1
     */
    @Nullable
    public Long getAuthorId() {
        return mAuthorId;
    }

    /**
     * Checks whether comments are disabled or not
     *
     * @return true if comments are disabled, false otherwise
     * @since 1.0.0.1
     */
    public boolean isCommentsDisabled() {
        return mIsCommentsDisabled;
    }

    /**
     * Gets the label names of the article
     *
     * @return the label names of the article. The list will be unmodifiable.
     * @since 1.0.0.1
     */
    @NonNull
    public List<String> getLabelNames() {
        return mLabelNames;
    }

    /**
     * Checks if the article is a draft or not
     *
     * @return true if the article is a draft, false otherwise
     * @since 1.0.0.1
     */
    public boolean isDraft() {
        return mIsDraft;
    }

    /**
     * Gets the sum of votes. Unhelpful votes are taken as -1 value and helpful votes are taken as
     * +1 value.
     *
     * <p>
     *     For example, if there are 3 unhelpful votes and 5 helpful votes then the vote sum is
     *     (-1 * 3) + (+1 * 5) = 2
     * </p>
     *
     * @return The sum of votes. Unhelpful ones are -1, helpful ones are +1
     * @since 1.1.0.1
     */
    public int getVoteSum() {
        return mVoteSum;
    }

    /**
     * Gets the total number of votes that have been cast for this article.
     *
     * <p>
     *     For example, if there are 3 unhelpful votes and 5 helpful votes then the total number
     *     of votes is 3 + 5 = 8.
     * </p>
     *
     * @return the total number of votes that have been cast for this article.
     * @since 1.0.0.1
     */
    public int getVoteCount() {
        return mVoteCount;
    }

    /**
     * Gets the ID of the section that the article belongs to
     *
     * @return the ID of the section that the article belongs to
     * @since 1.0.0.1
     */
    @Nullable
    public Long getSectionId() {
        return mSectionId;
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
     * Gets the title of the article
     *
     * @return the title of the article
     * @since 1.0.0.1
     */
    @Nullable
    public String getTitle() {
        return mTitle;
    }

    /**
     * Gets the body of the article in plain text
     *
     * @return the body of the article in plain text
     * @since 1.0.0.1
     */
    @Nullable
    public String getBody() {
        return mBody;
    }

    /**
     * Gets the source locale of the article in the form of ll-CC, e.g. en-US
     *
     * @return the source locale of the article in the form of ll-CC, e.g. en-US
     * @since 1.0.0.1
     */
    @Nullable
    public String getSourceLocale() {
        return mSourceLocale;
    }

    /**
     * Gets the locale of the article in the form of ll-CC, e.g. en-US
     *
     * @return the locale of the article in the form of ll-CC, e.g. en-US
     * @since 1.0.0.1
     */
    @Nullable
    public String getLocale() {
        return mLocale;
    }

    /**
     * Checks whether the article is outdated or not
     *
     * @return true if the article is outdated, false otherwise
     * @since 1.0.0.1
     */
    public boolean isOutdated() {
        return mIsOutdated;
    }


    /**
     * Gets the number of upvotes for this article.
     *
     * <p>
     *     The formula that is used is {@code ((vote_sum + vote_count) / 2)}
     * </p>
     *
     * @since 1.2.0.1
     */
    public int getUpvoteCount() {
        return mUpVoteCount;
    }

    /**
     * Gets the number of downvotes for this article
     *
     * <p>
     *     The formula that is used is {@code abs(vote_sum - vote_count) / 2}
     * </p>
     *
     * @since 1.2.0.1
     */
    public int getDownvoteCount() {
        return mDownVoteCount;
    }
}
