package com.example.cf_sdk.changebankapi.parameter.member;




import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to retrieve document by id.
 */

public class DocumentParameters extends Parameters {

    private String mDocumentId;

    private transient long mMemberId;

    public static DocumentParameters create(String documentId) {
        return new DocumentParameters(new HashMap<String, String>(), documentId);
    }

    private DocumentParameters(Map<String, String> headers, String documentId) {
        super(headers);
        mDocumentId = documentId;
    }

    public String getDocumentId() {
        return mDocumentId;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
