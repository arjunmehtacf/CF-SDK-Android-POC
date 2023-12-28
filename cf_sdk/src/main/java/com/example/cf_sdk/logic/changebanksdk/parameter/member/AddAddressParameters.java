package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.model.member.Address;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Contains parameters to make a successful request to AddAddress.
 */

public class AddAddressParameters extends Parameters {
    @SerializedName("address1")
    private String mStreet;

    @SerializedName("address2")
    private String mUnit;

    @SerializedName("postalCode")
    private String mZipCode;

    @SerializedName("city")
    private String mCity;

    @SerializedName("state")
    private String mState;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("addressType")
    private String mAddressType="Home";

    @SerializedName("customerNumber")
    private String customerNumber;

    public static AddAddressParameters create(Map<String, String> headers,
                                              String street,
                                              String unit,
                                              String zipCode,
                                              String city,
                                              String state,
                                              String country,
                                              String addressType) {
        return new AddAddressParameters(headers, street, unit, zipCode, city, state, country,addressType);
    }

    public AddAddressParameters(Map<String, String> headers, String street, String unit,
                                String zipCode, String city, String state, String country,String addressType) {
        super(headers);

        mStreet = street;
        mUnit = unit;
        mZipCode = zipCode;
        mCity = city;
        mState = state;
        mCountry = country;
        mAddressType =addressType;
    }

    public void setMemberId(String cno) {
        customerNumber = cno;
    }

    public String getMemberId() {
        return customerNumber;
    }

    public String getStreet() {
        return mStreet;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getUnit() {
        return mUnit;
    }

    public Address getAddress() {
        return Address.create(mStreet, mUnit, mCity, mZipCode, mState);
    }
}
