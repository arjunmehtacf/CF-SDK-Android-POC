package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.account.AchAccount;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Response for AchAccountsApi
 */

public class AchAccountsApiResponse extends ChangebankResponse {
    @SerializedName("accounts")
    private List<AchAccount> mAchAccounts = null;

    public static AchAccountsApiResponse create(List<AchAccount> achAccountWithBank) {
        return new AchAccountsApiResponse(achAccountWithBank);
    }

    private AchAccountsApiResponse(List<AchAccount> achAccountWithBank) {
        mAchAccounts = achAccountWithBank;
    }

    public List<AchAccount> getAchAccounts() {
        return mAchAccounts;
    }
}
