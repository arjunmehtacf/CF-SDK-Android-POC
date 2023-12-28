package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Api response object for retrieving a document.
 */

public class DocumentApiResponse {

    @SerializedName("documentId")
    private String mDocumentId;

    @SerializedName("name")
    private String mName;

    @SerializedName("document")
    private Document mDocument;

    @SerializedName("status")
    private String mStatus;

    public DocumentApiResponse(Document document, String documentId) {
        mDocument = document;
        mDocumentId = documentId;
    }

    public static DocumentApiResponse create(Document document, String documentId) {
        return new DocumentApiResponse(document, documentId);
    }

    public String getDocumentId() {
        return mDocumentId;
    }

    public String getName() {
        return mName;
    }

    public Document getDocument() {
        return mDocument;
    }

    public String getStatus() {
        return mStatus;
    }


}
