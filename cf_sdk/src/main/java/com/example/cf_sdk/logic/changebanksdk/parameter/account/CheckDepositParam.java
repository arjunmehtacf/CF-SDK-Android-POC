package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains request parameter for deosit check api.
 */
public class CheckDepositParam extends Parameters {

    @SerializedName("frontImage")
    private String mFronImage;

    @SerializedName("backImage")
    private String mBackImage;

    @SerializedName("amount")
    private String mAmount;

    private long mMemberId;


    private CheckDepositParam(Map<String, String> headers,
                              String frontImage,
                              String backImage,
                              String amount
    ) {
        super(headers);
        mFronImage = frontImage;
        mBackImage = backImage;
        mAmount = amount;
    }


    public String getmFronImage() {
        return mFronImage;
    }

    public String getmBackImage() {
        return mBackImage;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public static CheckDepositParam create(
            String fronImage,
            String mBackImage,
            String mAmount
    ) {
        Map<String,String> mHeader = new HashMap<>();
        return new CheckDepositParam(mHeader, fronImage, mBackImage, mAmount);
    }
}

