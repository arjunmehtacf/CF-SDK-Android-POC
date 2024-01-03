package com.example.cf_sdk.changebankapi.model.member;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * Member physical Address.
 */

public class Address implements Serializable {
    public static final Address EMPTY = new Address();

    @SerializedName(value = "addressLine1", alternate = "address1")
    private String mAddressLine1;

    @SerializedName(value = "addressLine2", alternate = "address2")
    private String mAddressLine2;

    @SerializedName("city")
    private String mCity;

    @SerializedName("postalCode")
    private String mZipCode;

    @SerializedName("state")
    private String mState;

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @SerializedName("addressType")
    private String addressType;

    public static Address create() {
        return EMPTY;
    }

    public static Address create(String addressLine1,
                          String addressLine2,
                          String city,
                          String zipCode,
                          String state) {
        return new Address(addressLine1, addressLine2, city, zipCode, state);
    }

    private Address(String addressLine1,
                    String addressLine2,
                    String city,
                    String zipCode,
                    String state) {
        mAddressLine1 = addressLine1;
        mAddressLine2 = addressLine2;
        mCity = city;
        mZipCode = zipCode;
        mState = state;
    }

    public Address() {
        mAddressLine1 = "";
        mAddressLine2 = "";
        mCity = "";
        mZipCode = "";
        mState = "";
    }

    public String getAddressLine1() {
        return mAddressLine1;
    }

    public Address withAddressLine1(String addressLine1) {
        return new Address(addressLine1, mAddressLine2, mCity, mZipCode, mState);
    }

    public String getAddressLine2() {
        return mAddressLine2;
    }

    public Address withAddressLine2(String addressLine2) {
        return new Address(mAddressLine1, addressLine2, mCity, mZipCode, mState);
    }

    public String getCity() {
        return mCity;
    }

    public Address withCity(String city) {
        return new Address(mAddressLine1, mAddressLine2, city, mZipCode, mState);
    }

    public String getZipCode() {
        return mZipCode;
    }

    public Address withZipCode(String zipCode) {
        return new Address(mAddressLine1, mAddressLine2, mCity, zipCode, mState);
    }

    public String getState() {
        return mState;
    }

    public Address withState(String state) {
        return new Address(mAddressLine1, mAddressLine2, mCity, mZipCode, state);
    }

    public void setAddressLine1(String mAddressLine1) {
        this.mAddressLine1 = mAddressLine1;
    }

    public void setAddressLine2(String mAddressLine2) {
        this.mAddressLine2 = mAddressLine2;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public void setZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (mAddressLine1 != null ?
                !mAddressLine1.equals(address.mAddressLine1) :
                address.mAddressLine1 != null) {
            return false;
        }

        if (mAddressLine2 != null ?
                !mAddressLine2.equals(address.mAddressLine2) :
                address.mAddressLine2 != null) {
            return false;
        }

        if (mCity != null ?
                !mCity.equals(address.mCity) :
                address.mCity != null) {
            return false;
        }

        if (mZipCode != null ?
                !mZipCode.equals(address.mZipCode) :
                address.mZipCode != null) {
            return false;
        }

        return mState != null ?
                mState.equals(address.mState) :
                address.mState == null;
    }

    @Override
    public int hashCode() {
        int result = mAddressLine1 != null ? mAddressLine1.hashCode() : 0;
        result = 31 * result + (mAddressLine2 != null ? mAddressLine2.hashCode() : 0);
        result = 31 * result + (mCity != null ? mCity.hashCode() : 0);
        result = 31 * result + (mZipCode != null ? mZipCode.hashCode() : 0);
        result = 31 * result + (mState != null ? mState.hashCode() : 0);
        return result;
    }
}
