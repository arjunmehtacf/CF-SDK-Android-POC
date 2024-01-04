package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for Bank, contains fields describing the bank properties.
 */

public class Bank {
    public static final String INVALID_ID = "-1";

    @SerializedName("account_id")
    private String mId = "";

    @SerializedName("bankName")
    private String mName = "";

    @SerializedName("bankType")
    private BankType mBankType;

    @SerializedName("url")
    private String mUrl = "";

    @SerializedName("image")
    private String mImage = "";

    @SerializedName("imageExt")
    private String mImageExt = "";

    @SerializedName("code")
    private String mCode = "";

    @SerializedName("mxId")
    private String mMxId = "";

    @SerializedName("isHidden")
    private Boolean mIsHidden = false;

    @SerializedName("hasCheckingAccounts")
    private Boolean mHasCheckingAccounts = false;

    @SerializedName("hasSavingsAccounts")
    private Boolean mHasSavingsAccounts = false;

    @SerializedName("hasCreditCardAccounts")
    private Boolean mHasCreditCardAccounts = false;

    @SerializedName("hasInvestmentAccounts")
    private Boolean mHasInvestmentAccounts = false;

    @SerializedName("hasLoadAccounts")
    private Boolean mHasLoadAccounts = false;

    @SerializedName("hasMortgageAccounts")
    private Boolean mHasMortgageAccounts = false;

    @SerializedName("hasLineOfCreditAccounts")
    private Boolean mHasLineOfCreditAccounts = false;

    @SerializedName("routingNumbers")
    private List<String> mRoutingNumbers = null;

    @SerializedName("institutionCredential")
    private List<InstitutionCredential> mInstitutionCredential = null;

    public static Bank create(String id, String name, BankType bankType) {
        return new Bank(id, name, bankType);
    }

    private Bank(String id, String name, BankType bankType) {
        mId = id;
        mName = name;
        mBankType = bankType;
    }

    public Bank(
            String id,
            String name,
            BankType bankType,

            String url,
            String image,
            String imageExt,
            String code,
            String mxId,
            Boolean isHidden,
            Boolean hasCheckingAccounts,
            Boolean hasSavingsAccounts,
            Boolean hasCreditCardAccounts,
            Boolean hasInvestmentAccounts,
            Boolean hasLoadAccounts,
            Boolean hasMortgageAccounts,
            Boolean hasLineOfCreditAccounts,
            List<String> routingNumbers,
            List<InstitutionCredential> institutionCredential) {
        mId = id;
        mName = name;
        mBankType = bankType;
        mUrl = url;
        mImage = image;
        mImageExt = imageExt;
        mCode = code;
        mMxId = mxId;
        mIsHidden = isHidden;
        mHasCheckingAccounts = hasCheckingAccounts;
        mHasSavingsAccounts = hasSavingsAccounts;
        mHasCreditCardAccounts = hasCreditCardAccounts;
        mHasInvestmentAccounts = hasInvestmentAccounts;
        mHasLoadAccounts = hasLoadAccounts;
        mHasMortgageAccounts = hasMortgageAccounts;
        mHasLineOfCreditAccounts = hasLineOfCreditAccounts;
        mRoutingNumbers = routingNumbers;
        mInstitutionCredential = institutionCredential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return mId != null ? mId.equals(bank.mId) : bank.mId == null;
    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public BankType getBankType() {
        return mBankType;
    }

    public String getUrl() {
        return mUrl;
    }

    public Bank withUrl(String url) {
        return new Bank(
                mId,
                mName,
                mBankType,
                url,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public String getImage() {
        return mImage;
    }

    public Bank withImage(String image) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                image,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public String getImageExt() {
        return mImageExt;
    }

    public Bank withImageExt(String imageExt) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                imageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public String getCode() {
        return mCode;
    }

    public Bank withCode(String code) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                code,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public String getMxId() {
        return mMxId;
    }

    public Bank withMxId(String mxId) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getIsHidden() {
        return mIsHidden;
    }

    public Bank withIsHidden(Boolean isHidden) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                isHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasCheckingAccounts() {
        return mHasCheckingAccounts;
    }

    public Bank setHasCheckingAccounts(Boolean hasCheckingAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                hasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasSavingsAccounts() {
        return mHasSavingsAccounts;
    }

    public Bank withHasSavingsAccounts(Boolean hasSavingsAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                hasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasCreditCardAccounts() {
        return mHasCreditCardAccounts;
    }

    public Bank withHasCreditCardAccounts(Boolean hasCreditCardAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                hasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasInvestmentAccounts() {
        return mHasInvestmentAccounts;
    }

    public Bank withHasInvestmentAccounts(Boolean hasInvestmentAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasInvestmentAccounts,
                mHasCreditCardAccounts,
                hasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasLoadAccounts() {
        return mHasLoadAccounts;
    }

    public Bank withHasLoadAccounts(Boolean hasLoadAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasInvestmentAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                hasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasMortgageAccounts() {
        return mHasMortgageAccounts;
    }

    public Bank withHasMortgageAccounts(Boolean hasMortgageAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasSavingsAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                hasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public Boolean getHasLineOfCreditAccounts() {
        return mHasLineOfCreditAccounts;
    }

    public Bank setHasLineOfCreditAccounts(Boolean hasLineOfCreditAccounts) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasInvestmentAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                hasLineOfCreditAccounts,
                mRoutingNumbers,
                mInstitutionCredential);
    }

    public List<String> getRoutingNumbers() {
        return mRoutingNumbers;
    }

    public Bank withRoutingNumbers(List<String> routingNumbers) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasInvestmentAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                routingNumbers,
                mInstitutionCredential);
    }

    public List<InstitutionCredential> getInstitutionCredential() {
        return mInstitutionCredential;
    }

    public Bank withInstitutionCredential(List<InstitutionCredential> institutionCredential) {
        return new Bank(
                mId,
                mName,
                mBankType,
                mUrl,
                mImage,
                mImageExt,
                mCode,
                mMxId,
                mIsHidden,
                mHasCheckingAccounts,
                mHasInvestmentAccounts,
                mHasCreditCardAccounts,
                mHasInvestmentAccounts,
                mHasLoadAccounts,
                mHasMortgageAccounts,
                mHasLineOfCreditAccounts,
                mRoutingNumbers,
                institutionCredential);
    }
}