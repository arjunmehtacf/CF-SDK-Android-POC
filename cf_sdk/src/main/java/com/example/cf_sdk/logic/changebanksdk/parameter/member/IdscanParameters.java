package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

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

    public static IdscanParameters create(@Nonnull String country,
                                          @Nonnull String front,
                                          @Nonnull String back,
                                          @Nonnull String selfie) {
        return new IdscanParameters(
                new HashMap<String, String>(),
                country,
                front,
                back,
                selfie);
    }

    private IdscanParameters(Map<String, String> headers,
                             @Nonnull String country,
                             @Nonnull String front,
                             @Nonnull String back,
                             @Nonnull String selfie) {
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
