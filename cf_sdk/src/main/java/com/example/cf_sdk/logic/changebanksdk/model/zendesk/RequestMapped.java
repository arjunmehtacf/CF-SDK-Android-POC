package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by gunveernatt on 6/4/18.
 */

public class RequestMapped implements Serializable, Comparable<RequestMapped> {


    private String mId;
    private String mUrl;
    private String mSubject;
    private String mDescription;
    private String mPriority;
    private String mType;
    private Long mOrganizationId;
    private Long mRequesterId;
    private List<Long> mCollaboratorsIds;
    private Date mDueAt;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private Date mPublicUpdatedAt;
    private Integer mCommentCount;
    private CommentMapped mLastComment;
    private CommentMapped mFirstComment;
    private List<UserMapped> mLastCommentingAgents;
    private RequestStatusMapped mStatus;
    private boolean mHasUnreadMessages = false;

    public RequestMapped(
            String id,
            String url,
            String subject,
            String description,
            String priority,
            String type,
            Long organizationId,
            Long requesterId,
            List<Long> collaboratorsIds,
            Date dueAt,
            Date createdAt,
            Date updatedAt,
            Date publicUpdatedAt,
            Integer commentCount,
            CommentMapped lastComment,
            CommentMapped firstComment,
            List<UserMapped> lastCommentingAgents,
            RequestStatusMapped status) {

        this.mId = id;
        this.mUrl = url;
        this.mSubject = subject;
        this.mDescription = description;
        this.mPriority = priority;
        this.mType = type;
        this.mOrganizationId = organizationId;
        this.mRequesterId = requesterId;
        this.mCollaboratorsIds = collaboratorsIds;
        this.mDueAt = dueAt;
        this.mCreatedAt = createdAt;
        this.mUpdatedAt = updatedAt;
        this.mPublicUpdatedAt = publicUpdatedAt;
        this.mCommentCount = commentCount;
        this.mLastComment = lastComment;
        this.mFirstComment = firstComment;
        this.mLastCommentingAgents = lastCommentingAgents;
        this.mStatus = status;
    }


    public void setHasUnreadMessages() {
        this.mHasUnreadMessages = true;
    }

    public boolean hasUnreadMessages() {
        return mHasUnreadMessages;
    }

    /**
     * Gets the ID of the Request
     *
     * @return the ID of the Request
     */
    @Nullable
    public String getId() {
        return mId;
    }

    /**
     * Gets the API URL of the Request
     *
     * @return the API url of the Request
     */
    @Nullable
    public String getUrl() {
        return mUrl;
    }

    /**
     * Gets the subject of the Request
     *
     * @return the subject of the Request
     */
    @Nullable
    public String getSubject() {
        return mSubject;
    }

    /**
     * Gets the description of the Request
     *
     * @return the description of the Request
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    /**
     * Gets the status of the Request
     *
     * @return the status of the Request
     */
    @Nullable
    public RequestStatusMapped getStatus() {
        return mStatus;
    }

    /**
     * Gets the priority of the Request
     *
     * @return the priority of the Request
     */
    @Nullable
    public String getPriority() {
        return mPriority;
    }

    /**
     * Gets the type of the Request
     *
     * @return the type of the Request
     */
    @Nullable
    public String getType() {
        return mType;
    }

    /**
     * Gets the ID of the Organization that the Request is associated with
     *
     * @return the ID of the Organization that the Request is associated with
     */
    @Nullable
    public Long getOrganizationId() {
        return mOrganizationId;
    }

    /**
     * Gets the ID of the user who requested the Request
     *
     * @return the ID of the user who requested the Request
     */
    @Nullable
    public Long getRequesterId() {
        return mRequesterId;
    }

    /**
     * Gets the IDs of all of the users who are collaborators on the Request
     *
     * @return the IDs of all of the users who are collaborators on the Request
     */
    @NonNull
    public List<Long> getCollaboratorIds() {
        return mCollaboratorsIds;
    }

    /**
     * Gets the date that the Request is due at
     *
     * @return the date that the Request was created at
     */
    @Nullable
    public Date getDueAt() {
        return mDueAt;
    }

    /**
     * Gets the date that the Request was created at
     *
     * @return the date that the Request was created at
     */
    @Nullable
    public Date getCreatedAt() {
        return mCreatedAt;
    }

    /**
     * Gets the date that the Request was updated at
     *
     * @return the date that the Request was updated at
     */
    @Nullable
    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    /**
     * Gets the date that the Request was publicly updated at
     *
     * @return the date that the Request was publicly updated at
     */
    @Nullable
    public Date getPublicUpdatedAt() {
        return mPublicUpdatedAt;
    }


    /**
     * Gets the number of comments on the Request
     *
     * @return the number of comments on the Request
     */
    @Nullable
    public Integer getCommentCount() {
        return mCommentCount;
    }

    /**
     * Gets the last comment from the Request.
     *
     * @return the last comment from the Request
     */
    @Nullable
    public CommentMapped getLastComment() {
        return mLastComment;
    }

    /**
     * Gets the first comment from the Request.
     *
     * @return the first comment from the Request
     */
    @Nullable
    public CommentMapped getFirstComment() {
        return mFirstComment;
    }

    /**
     * Gets the last 5 commenting agents.
     *
     * @return the list of the last 5 commenting agents.
     */
    public List<UserMapped> getLastCommentingAgents() {
        return mLastCommentingAgents;
    }

    @Override
    public int compareTo(@NonNull RequestMapped o) {
        return o.getUpdatedAt().compareTo(getUpdatedAt());
    }
}
