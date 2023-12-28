package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Status for an {@link AchAccount}.
 */
public enum AchAccountStatus {
    NONE(""),

    @SerializedName("LOCKED")
    LOCKED("Locked"),

    @SerializedName("AUTHENTICATING")
    AUTHENTICATING("Authenticating"),

    @SerializedName("MFA_REQUIRED")
    MFA_REQUIRED("Mfa Required"),

    @SerializedName("PROCESSING")
    PROCESSING("Processing"),

    @SerializedName("FAILED")
    FAILED("Failed"),

    @SerializedName("PENDING")
    PENDING("Pending"),

    @SerializedName("REFRESHING")
    REFRESHING("Refreshing"),

    @SerializedName("LINKED")
    LINKED("Linked"),

    @SerializedName("UNLINKED")
    UNLINKED("Unlinked"),

    @SerializedName("DELETED")
    DELETED("Deleted"),

    @SerializedName("UNAUTHENTICATED")
    UNAUTHENTICATED("Unauthenticated"),

    @SerializedName("ACTIVE")
    ACTIVE("Active"),

    @SerializedName("CANCEL")
    CANCEL("Cancel");

    String mStatusName;

    AchAccountStatus(String statusName) {
        mStatusName = statusName;
    }

    public String getStatusName() {
        return mStatusName;
    }

    public boolean isTransitional() {
        return this == AchAccountStatus.REFRESHING ||
                this == AchAccountStatus.PROCESSING ||
                this == AchAccountStatus.AUTHENTICATING ||
                this == AchAccountStatus.DELETED;
    }
}
