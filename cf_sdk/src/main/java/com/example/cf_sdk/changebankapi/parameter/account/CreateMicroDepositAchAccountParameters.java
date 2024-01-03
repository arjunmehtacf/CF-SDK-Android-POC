package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to confirm Ach Account.
 */

public class CreateMicroDepositAchAccountParameters extends Parameters {

    @SerializedName("bankAccountNumber")
    private String mAccountNumber;

    @SerializedName("routing")
    private String mRoutingNumber;

    @SerializedName("firstName")
    private String mFirstName;

    @SerializedName("lastName")
    private String mLastName;

    @SerializedName("style")
    public String mAccountType;

    @SerializedName("accountNumber")
    private String mMemberId;

    @SerializedName("bankName")
    String bankName;

    public String getBankName() {
        return bankName;
    }

    public String getBankAccountName() {
        bankAccountName = mFirstName + " " + mLastName;
        return bankAccountName;
    }

    @SerializedName("bankAccountName")
    private String bankAccountName;



    private CreateMicroDepositAchAccountParameters(Map<String, String> headers, String accountNumber, String routingNumber,
                                                   String firstName, String lastName, String accountType,String bankname) {
        super(headers);

        mAccountNumber = accountNumber;
        mRoutingNumber = routingNumber;
        mFirstName = firstName;
        mLastName = lastName;
        mAccountType = accountType;
        bankAccountName = mFirstName + " " + mLastName;
        bankName = bankname;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public void setMemberId(String mMemberId) {
        this.mMemberId = mMemberId;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public String getRoutingNumber() {
        return mRoutingNumber;
    }


    public static CreateMicroDepositAchAccountParameters create(
            String accountNumber, String routingNumber,
            String firstName, String lastName, String accountType,String bankname) {
        return new CreateMicroDepositAchAccountParameters(new HashMap<String, String>(), accountNumber, routingNumber, firstName, lastName, accountType,bankname);
    }
}
