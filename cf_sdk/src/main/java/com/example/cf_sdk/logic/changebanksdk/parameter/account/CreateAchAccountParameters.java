package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.model.account.Credential;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 *
 * Create ach account request parameter.
 */

public class CreateAchAccountParameters extends Parameters {

    @SerializedName("memberId")
    private Long mMemberId;

    @SerializedName("bankId")
    private String mBankId;

    @SerializedName("credentials")
    private List<Credential> mCredentials = null;

    public CreateAchAccountParameters(Map<String, String> headers,
                                      Long memberId,
                                      String bankId,
                                      List<Credential> credentials) {
        super(headers);
        mMemberId = memberId;
        mBankId = bankId;
        mCredentials = credentials;
    }

    public static CreateAchAccountParameters create(Map<String, String> headers,
                                                    String bankId,
                                                    List<Credential> credentials) {
        return new CreateAchAccountParameters(headers, null, bankId, credentials);
    }


    public CreateAchAccountParameters withMemberId(Long memberId) {
        return new CreateAchAccountParameters(getHeaders(), memberId, mBankId, mCredentials);
    }

    public List<Credential> getCredentials() {
        return mCredentials;
    }

    public Long getMemberId() {
        return mMemberId;
    }

    public String getBankId() {
        return mBankId;
    }
}
