package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.account.AchTransfer;
import com.google.gson.annotations.SerializedName;

/**
 *
 * Representation of an {@link AchTransfer} response from ChangeApi.
 */

public class AchTransferResponse {
    @SerializedName("achTransfer")
    private AchTransfer mAchTransfer;

    @SerializedName("verificationId")
    private String mVerificationId;

    public static AchTransferResponse create(AchTransfer achTransfer, String verificationId) {
        return new AchTransferResponse(achTransfer, verificationId);
    }

    private AchTransferResponse(AchTransfer achTransfer, String verificationId) {
        mAchTransfer = achTransfer;
        mVerificationId = verificationId;
    }

    public AchTransfer getAchTransferHistory() {
        return mAchTransfer;
    }

    public String getVerificationId() {
        return mVerificationId;
    }
}
