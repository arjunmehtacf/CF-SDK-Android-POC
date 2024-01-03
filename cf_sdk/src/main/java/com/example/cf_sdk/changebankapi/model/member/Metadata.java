package com.example.cf_sdk.changebankapi.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Metadata object to describe the content returned for a document.
 */

public class Metadata {

    @SerializedName("pages")
    private Long mPages;

    @SerializedName("encrypted")
    private Boolean mEncrypted;

    @SerializedName("dateCreated")
    private Long mDateCreated;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("extension")
    private String mExtension;

    public Long getPages() {
        return mPages;
    }

    public Boolean getEncrypted() {
        return mEncrypted;
    }

    public Long getDateCreated() {
        return mDateCreated;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getExtension() {
        return mExtension;
    }

}
