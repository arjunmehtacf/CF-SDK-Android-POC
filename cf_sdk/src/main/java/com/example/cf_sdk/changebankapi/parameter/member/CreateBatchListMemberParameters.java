package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters to make a successful request to create a batch list member.
 */

public class CreateBatchListMemberParameters extends Parameters {

    @SerializedName("adminId")
    private final String mAdminNumber;

    @SerializedName("password")
    private final String mPassword;

    @SerializedName("pan4")
    private final String mPan4;

//    @SerializedName("programRefCode")
//    private final String programRefCode = "QU";

    @SerializedName("username")
    private final String mUsername;

    @SerializedName("transferBalance")
    private final Boolean transferBalance;

    @SerializedName("eSignAgreementAccepted")
    private Boolean eSignAgreementAccepted = null;


    private transient final boolean mRememberUsername;

    private transient final String mDob;
    private transient final String mLast4SSN;

    public boolean isRememberUsername() {
        return mRememberUsername;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getAdminNumber() {
        return mAdminNumber;
    }

    public String getDob() {
        return mDob;
    }

    public String getLast4SSN() {
        return mLast4SSN;
    }

    public String getmPan4(){
        return mPan4;
    }

    public void setESignAgreementAccepted(Boolean eSignAgreementAccepted) {
        this.eSignAgreementAccepted = eSignAgreementAccepted;
    }

    public static CreateBatchListMemberParameters create(String adminNumber, String password,
                                                         String username, boolean rememberUsername, boolean balanceTransfer) {
        return new CreateBatchListMemberParameters(new HashMap<String, String>(), adminNumber, password, username, rememberUsername, null, null, balanceTransfer,null);
    }


    public CreateBatchListMemberParameters(Map<String, String> headers, String adminNumber, String password,
                                           String username, boolean rememberUsername, String dob, String last4Ssn, Boolean balanceTransfer,String pan4) {
        super(headers);

        mAdminNumber = adminNumber;
        mPassword = password;
        mUsername = username != null ? username.toLowerCase() : null;
        mRememberUsername = rememberUsername;
        mDob = dob;
        mLast4SSN = last4Ssn;
        transferBalance = balanceTransfer;
        mPan4 = pan4;
    }

    public static CreateBatchListMemberParameters create(String adminNumber, String dob, String last4Ssn) {
        return new CreateBatchListMemberParameters(new HashMap<String, String>(), adminNumber, null, null, false, dob, last4Ssn, null,null);
    }

    public static CreateBatchListMemberParameters create(String adminNumber,String pan4) {
        return new CreateBatchListMemberParameters(new HashMap<String, String>(), adminNumber, null, null, false, null, null, null,pan4);
    }
}
