package com.example.cf_sdk.changebankapi.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;


public class CommentMapped {

    private String mUrl = "";
    private Long mId=0L;
    private String mRequestId = "";
    private String mBody = "";
    private String mHtmlBody = "";
    private boolean mIsPublic =false;
    private Long mAuthorId=0L;
    private List<String> mAttachments;
    private Date mCreatedAt;

    public CommentMapped(String url, Long id, String requestId, String body, String htmlBody, boolean isPublic, Long authorId, List<String> attachments, Date createdAt) {
        this.mUrl = url;
        this.mId = id;
        this.mRequestId = requestId;
        this.mBody = body;
        this.mHtmlBody = htmlBody;
        this.mIsPublic = isPublic;
        this.mAuthorId = authorId;
        this.mAttachments = attachments;
        this.mCreatedAt = createdAt;
    }

    /**
     * Gets the API URL of the comment
     *
     * @return the API url of the comment
     */
    @Nullable
    public String getUrl() {
        return mUrl;
    }

    /**
     * Gets the ID of the comment
     *
     * @return the ID of the comment
     */
    @Nullable
    public Long getId() {
        return mId;
    }

    /**
     * Gets the ID of the request that this comment belongs to
     *
     * @return the ID of the request that this comment belongs to
     */
    @Nullable
    public String getRequestId() {
        return mRequestId;
    }

    /**
     * Gets the body of the comment in plain text
     *
     * @return the body of the comment in plain text
     */
    @Nullable
    public String getBody() {
        return mBody;
    }

 
    /**
     * Gets the HTML body of the comment
     *
     * @return the HTML body of the comment
     */
    @Nullable
    public String getHtmlBody() {
        return mHtmlBody;
    }

    /**
     * Checks if this comment is public or not
     *
     * @return true if the comment is public, false otherwise
     */
    public boolean isPublic() {
        return mIsPublic;
    }

    /**
     * Gets the ID of the author who created the comment.
     *
     * @return the ID of the author who created the comment.
     */
    @Nullable
    public Long getAuthorId() {
        return mAuthorId;
    }

    /**
     * Gets the attachments of the comment
     *
     * @return the attachments of the comment
     */
    @NonNull
    public List<String> getAttachments() {
        return mAttachments;
    }


    /**
     * Gets the date that the comment was created at
     *
     * @return the date that the comment was created at
     */
    @Nullable
    public Date getCreatedAt() {
        return mCreatedAt;
    }


}
