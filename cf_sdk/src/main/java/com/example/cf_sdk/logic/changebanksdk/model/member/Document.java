package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Document object.
 */

public class Document {

    @SerializedName("content")
    private String mContent;

    @SerializedName("metadata")
    private Metadata mMetadata;

    public Document() {
    }

    private Document(String encodedImage) {
        mContent = encodedImage;
    }

    public static Document Create(String encodedImage) {
        return new Document(encodedImage);
    }

    public String getContent() {
        return mContent;
    }

    public Metadata getMetadata() {
        return mMetadata;
    }

}
