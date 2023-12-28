package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Model for Bank, contains fields describing the bank properties.
 */

public class Bank implements Parcelable {
    public static final String INVALID_ID = "-1";

    @SerializedName("account_id")
    private String mId;

    @SerializedName("bankName")
    private String mName;

    @SerializedName("bankType")
    private BankType mBankType;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("image")
    private String mImage;

    @SerializedName("imageExt")
    private String mImageExt;

    @SerializedName("code")
    private String mCode;

    @SerializedName("mxId")
    private String mMxId;

    @SerializedName("isHidden")
    private Boolean mIsHidden;

    @SerializedName("hasCheckingAccounts")
    private Boolean mHasCheckingAccounts;

    @SerializedName("hasSavingsAccounts")
    private Boolean mHasSavingsAccounts;

    @SerializedName("hasCreditCardAccounts")
    private Boolean mHasCreditCardAccounts;

    @SerializedName("hasInvestmentAccounts")
    private Boolean mHasInvestmentAccounts;

    @SerializedName("hasLoadAccounts")
    private Boolean mHasLoadAccounts;

    @SerializedName("hasMortgageAccounts")
    private Boolean mHasMortgageAccounts;

    @SerializedName("hasLineOfCreditAccounts")
    private Boolean mHasLineOfCreditAccounts;

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

    // TODO: Remove serializable from Bank model.
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeValue(mHasMortgageAccounts);
        dest.writeString(mUrl);
        dest.writeString(mImage);
        dest.writeValue(mIsHidden);
        dest.writeString(mImageExt);
        dest.writeString(mCode);
        dest.writeValue(mHasSavingsAccounts);
        dest.writeString(mId);
        dest.writeValue(mHasInvestmentAccounts);
        dest.writeValue(mHasCreditCardAccounts);
        dest.writeSerializable(mBankType);
        dest.writeStringList(mRoutingNumbers);
        dest.writeList(mInstitutionCredential);
        dest.writeValue(mHasLoadAccounts);
        dest.writeValue(mHasCheckingAccounts);
        dest.writeString(mMxId);
        dest.writeValue(mHasLineOfCreditAccounts);
    }

    protected Bank(Parcel in) {
        mName = in.readString();
        mHasMortgageAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mUrl = in.readString();
        mImage = in.readString();
        mIsHidden = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mImageExt = in.readString();
        mCode = in.readString();
        mHasSavingsAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mId = in.readString();
        mHasInvestmentAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mHasCreditCardAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mBankType = (BankType) in.readSerializable();
        mRoutingNumbers = in.createStringArrayList();
        mInstitutionCredential = new ArrayList<>();
        in.readList(mInstitutionCredential, InstitutionCredential.class.getClassLoader());
        mHasLoadAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mHasCheckingAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
        mMxId = in.readString();
        mHasLineOfCreditAccounts = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Bank> CREATOR = new Creator<Bank>() {
        @Override
        public Bank createFromParcel(Parcel source) {
            return new Bank(source);
        }

        @Override
        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };
}