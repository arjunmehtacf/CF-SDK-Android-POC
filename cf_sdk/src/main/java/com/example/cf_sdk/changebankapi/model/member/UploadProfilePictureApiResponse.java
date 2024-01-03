package com.example.cf_sdk.changebankapi.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Api response object for upload Profile Picture.
 */

public class UploadProfilePictureApiResponse {

    @SerializedName("document_id")
    private String mDocumentId = "";


    public UploadProfilePictureApiResponse(String documentId) {
        mDocumentId = documentId;
    }

    public static UploadProfilePictureApiResponse create(String documentId) {
        return new UploadProfilePictureApiResponse(documentId);
    }

    public String getDocumentId() {
        return mDocumentId;
    }

}
