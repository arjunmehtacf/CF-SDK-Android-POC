package com.example.cf_sdk.changebankapi.model.member;

import com.example.sdk_no_dagger.changebankapi.model.member.LastSuccessfulStage;
import com.example.sdk_no_dagger.changebankapi.model.member.ManualVerification;
import com.google.gson.annotations.SerializedName;

/**
 * Registration status response class
 */
public class RegistrationStatus {

    @SerializedName("lastSuccessfulStage")
    private LastSuccessfulStage mLastSuccessfulStage;

    @SerializedName("manualVerification")
    private ManualVerification mManualVerification;

    @SerializedName("idScanEnabled")
    private boolean mIdScanEnabled;

    @SerializedName("waitListed")
    private boolean mWaitlisted;

    @SerializedName("waitListRetry")
    private Boolean waitlistRetry;

    @SerializedName("cipEnabled")
    private Boolean cipEnabled;


    public RegistrationStatus(LastSuccessfulStage lastSuccessfulStage, ManualVerification manualVerification, boolean idScanEnabled, boolean waitlisted) {
        mLastSuccessfulStage = lastSuccessfulStage;
        mManualVerification = manualVerification;
        mIdScanEnabled = idScanEnabled;
        mWaitlisted = waitlisted;
        cipEnabled = false;
    }

    public static RegistrationStatus Create(ManualVerification manualVerification, boolean idScanEnabled) {
        return new RegistrationStatus(LastSuccessfulStage.MembershipCreated, manualVerification, idScanEnabled, false);
    }

    public static RegistrationStatus Create(ManualVerification manualVerification, boolean idScanEnabled, boolean waitlisted) {
        return new RegistrationStatus(LastSuccessfulStage.MembershipCreated, manualVerification, idScanEnabled, waitlisted);
    }

    public LastSuccessfulStage getLastSuccessfulStage() {
        return mLastSuccessfulStage;
    }

    public ManualVerification getManualVerification() {
        return mManualVerification;
    }

    public boolean isIdScanEnabled() {
        return mIdScanEnabled;
    }

    public boolean isWaitlisted() {
        return mWaitlisted;
    }

    public Boolean isWaitlistRetry() {
        return waitlistRetry;
    }

    public Boolean isCipEnabled() {
        return cipEnabled;
    }
}
