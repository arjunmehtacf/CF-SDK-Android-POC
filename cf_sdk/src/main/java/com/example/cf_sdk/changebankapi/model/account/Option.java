package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Option class used for displaying the multiple option choices for ach account linking.
 */


public class Option {

    @SerializedName("guid")
    private String mGuid = "";

    @SerializedName("label")
    private String mLabel = "";

    @SerializedName("value")
    private String mValue = "";

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String guid) {
        this.mGuid = guid;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        this.mValue = value;
    }

    public Option(String guid, String label, String value) {
        mGuid = guid;
        mLabel = label;
        mValue = value;
    }
}