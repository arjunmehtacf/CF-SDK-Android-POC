package com.example.cf_sdk.logic.changebanksdk.response;

import com.example.cf_sdk.logic.changebanksdk.model.account.Bank;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Response from {@link }
 */

public class BanksApiResponse {

    public static BanksApiResponse create(List<Bank> banks) {
        return new BanksApiResponse(banks);
    }

    public BanksApiResponse(List<Bank> banks){
        mBanks = banks;
    }

    @SerializedName("banks")
    private List<Bank> mBanks = null;

    public List<Bank> getBanks() {
        return mBanks;
    }
}
