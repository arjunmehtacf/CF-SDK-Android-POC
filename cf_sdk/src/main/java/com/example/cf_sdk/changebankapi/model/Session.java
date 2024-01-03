package com.example.cf_sdk.changebankapi.model;

import com.example.sdk_no_dagger.changebankapi.model.Role;
import com.google.gson.annotations.SerializedName;

/**
 *
 * Session model representing login response.
 */
public class Session {
    public static final Session INVALID_SESSION = Session.create("Invalid");

    @SerializedName("accessToken")
    private String mToken="";

    @SerializedName("refreshToken")
    private String mRefreshToken="";

    @SerializedName("tokenType")
    private String mTokenType="";

    @SerializedName("expires")
    private int mExpires=0;

    @SerializedName("idToken")
    private String mIdToken = "";

    @SerializedName("memberId")
    private long mMemberId=0L;

    @SerializedName("twoFactor")
    private boolean mNeedsTwoFactor=false;

    @SerializedName("role")
    private Role mRole;

    @SerializedName("features")
    private Features mFeatures;

    @SerializedName("tokenExpirationTime")
    private long mTokenExpirationTime;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @SerializedName("accountNumber")
    private String accountNumber="";

    public String getProgramId() {
        return programId;
    }
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    @SerializedName("programId")
    private String programId="";

    public static Session create(String token) {
        return new Session(token);
    }

    @SerializedName("customerNumber")
    private String customerNumber="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @SerializedName("username")
    private String username="";
    @SerializedName("allowTransfer")
    private boolean allowTransfer=false;

    public boolean isAllowTransfer() {
        return allowTransfer;
    }

    public void setAllowTransfer(boolean allowTransfer) {
        this.allowTransfer = allowTransfer;
    }

    public String getCustomerNumber(){
        return customerNumber;
    }

    public void setCustomerNumber(String mCusNumber)
    {
        customerNumber = mCusNumber;
    }
    private Session(String token) {
        mToken = token;
    }

    private Session(String token,
                    long memberId,
                    boolean needsTwoFactor,
                    Role role,
                    Features features,
                    long tokenExpirationTime,
                    String refreshToken,
                    String tokenType,
                    int expires,
                    String idToken) {
        mToken = token;
        mMemberId = memberId;
        mNeedsTwoFactor = needsTwoFactor;
        mRole = role;
        mFeatures = features;
        mTokenExpirationTime = tokenExpirationTime;
        mRefreshToken = refreshToken;
        mTokenType = tokenType;
        mExpires = expires;
        mIdToken = "";
        if(idToken != null) { mIdToken = idToken; }
    }

    @Override
    public String toString() {
        return "Session{" +
                "mMemberId=" + mMemberId +
                ", mToken='" + mToken + '\'' +
                ", mNeedsTwoFactor=" + mNeedsTwoFactor +
                ", mRole=" + mRole +
                ", mFeatures=" + mFeatures +
                '}';
    }

    public String getToken() {
        return mToken;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public String getIdToken() { return mIdToken; }

    public String getTokenType(){ return mTokenType; }

    public Session withMemberId(long memberId) {
        return new Session(
                mToken,
                memberId,
                mNeedsTwoFactor,
                mRole,
                mFeatures,
                mTokenExpirationTime,mRefreshToken,mTokenType,mExpires,mIdToken);
    }

    public boolean needsTwoFactor() {
        return mNeedsTwoFactor;
    }

    public Session withNeedsTwoFactor(boolean needsTwoFactor) {
        return new Session(
                mToken,
                mMemberId,
                needsTwoFactor,
                mRole,
                mFeatures,
                mTokenExpirationTime,mRefreshToken,mTokenType,mExpires,mIdToken);
    }

    public Role getRole() {
        return mRole;
    }

    public Session withRole(Role role) {
        return new Session(
                mToken,
                mMemberId,
                mNeedsTwoFactor,
                role,
                mFeatures,
                mTokenExpirationTime,mRefreshToken,mTokenType,mExpires,mIdToken);
    }

    public Features getFeatures() {
        return mFeatures;
    }

    public Session withFeatures(Features features) {
        return new Session(
                mToken,
                mMemberId,
                mNeedsTwoFactor,
                mRole,
                features,
                mTokenExpirationTime,mRefreshToken,mTokenType,mExpires,mIdToken);
    }

    public Session withTokenExpirationDate(long tokenExpirationDate) {
        return new Session(
                mToken,
                mMemberId,
                mNeedsTwoFactor,
                mRole,
                mFeatures,
                tokenExpirationDate,mRefreshToken,mTokenType,mExpires,mIdToken);
    }
}
