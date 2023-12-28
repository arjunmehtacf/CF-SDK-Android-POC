package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.model.account.Credential;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * The Request Parameters for Submitting the MFA or Retry of Ach Login.
 */

public class SubmitMfaOrNewBankCredentialsParameters extends Parameters {

    private transient String mAchAccountId;

    @SerializedName("credentials")
    private List<Credential> mCredentials = null;

    public SubmitMfaOrNewBankCredentialsParameters(Map<String, String> headers,
                                                   String achAccountId,
                                                   List<Credential> credentials) {
        super(headers);
        mAchAccountId = achAccountId;
        mCredentials = credentials;
    }

    public static SubmitMfaOrNewBankCredentialsParameters create(String achAccountId,
                                                                 List<Credential> credentials) {
        return new SubmitMfaOrNewBankCredentialsParameters(new HashMap<String, String>(), achAccountId, credentials);
    }


    public List<Credential> getCredentials() {
        return mCredentials;
    }

    public String getAchAccountId() {
        return mAchAccountId;
    }

}
