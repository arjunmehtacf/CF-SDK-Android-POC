package com.example.cf_sdk.logic.changebanksdk.model.google;

import java.io.Serializable;

/**
 *
 * Response model for address fields.
 */

public class Address implements Serializable {

    private String mStreet;
    private String mUnit;
    private String mZipCode;
    private String mCity;
    private String mState;
    private String mCountry;


    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        this.mStreet = street;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public String gemZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        this.mZipCode = zipCode;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        this.mState = state;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }
}
