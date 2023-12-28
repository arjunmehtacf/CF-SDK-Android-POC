package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by gunveernatt on 6/4/18.
 */

class AttachmentMapped {


    private String mUrl;
    private Long mId;
    private String mFileName;
    private String mContentUrl;
    private String mContentType;
    private Long mSize;
    private Long mWidth;
    private Long mHeight;
    private List<AttachmentMapped> mThumbnails;

    /**
     * Gets the API URL of the attachment
     *
     * @return the API URL of the attachment
     */
    @Nullable
    public String getUrl() {
        return mUrl;
    }

    /**
     * Gets the ID of the attachment
     *
     * @return the ID of the attachment
     */
    @Nullable
    public Long getId() {
        return mId;
    }

    /**
     * Gets the file name of the attachment
     *
     * @return the file name of the attachment
     */
    @Nullable
    public String getFileName() {
        return mFileName;
    }

    /**
     * Gets the URL at which the attachment can be downloaded
     *
     * @return the URL at which the attachment can be downloaded
     */
    @Nullable
    public String getContentUrl() {
        return mContentUrl;
    }

    /**
     * Gets the content type of the attachment, e.g. image/png
     *
     * @return the content type of the attachment, e.g. image/png
     */
    @Nullable
    public String getContentType() {
        return mContentType;
    }

    /**
     * Gets the size of the attachment in bytes
     *
     * @return the size of the attachment in bytes
     */
    @Nullable
    public Long getSize() {
        return mSize;
    }

    /**
     * Gets the width of the attachment in pixels
     *
     * @return the size of the attachment in pixels
     */
    @Nullable
    public Long getWidth() {
        return mWidth;
    }

    /**
     * Gets the height of the attachment in pixels
     *
     * @return the height of the attachment in pixels
     */
    @Nullable
    public Long getHeight() {
        return mHeight;
    }

    /**
     * Gets the thumbnails of the attachment
     *
     * @return the thumbnails of the attachment
     */
    @NonNull
    public List<AttachmentMapped> getThumbnails() {
        return mThumbnails;
    }

}
