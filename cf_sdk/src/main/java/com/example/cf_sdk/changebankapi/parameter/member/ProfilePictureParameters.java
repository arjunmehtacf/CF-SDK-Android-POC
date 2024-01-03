package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to retrieve document by id.
 */

public class ProfilePictureParameters extends Parameters {

    private String mDocumentId;

    private String mMemberId;

    public static ProfilePictureParameters create() {
        return new ProfilePictureParameters(new HashMap<String, String>());
    }

    private ProfilePictureParameters(Map<String, String> headers) {
        super(headers);
    }

    public String getDocumentId() {
        return mDocumentId;
    }

    public void setmDocumentId(String docid)
    {
        mDocumentId = docid;
    }

    public void setMemberId(String memberId) {
        mMemberId = memberId;
    }

    public String getMemberId() {
        return mMemberId;
    }
}
