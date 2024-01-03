package com.example.cf_sdk.changebankapi.parameter.member;

import androidx.annotation.NonNull;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Parameters for Idscan verification call.
 */

public class IdscanParameters extends Parameters {
    private transient long mMemberId;

    @SerializedName("countryCode")
    private final String mCountry;

    @SerializedName("image")
    private final String mFront;

    @SerializedName("backImage")
    private final String mBack;

    @SerializedName("faceImage")
    private final String mSelfie;

    public static IdscanParameters create(@NonNull String country,
                                          @NonNull String front,
                                          @NonNull String back,
                                          @NonNull String selfie) {
        return new IdscanParameters(
                new HashMap<String, String>(),
                country,
                front,
                back,
                selfie);
    }

    private IdscanParameters(Map<String, String> headers,
                             @NonNull String country,
                             @NonNull String front,
                             @NonNull String back,
                             @NonNull String selfie) {
        super(headers);

        mCountry = country;
        mFront = front;
        mBack = back;
        mSelfie = selfie;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }
}
