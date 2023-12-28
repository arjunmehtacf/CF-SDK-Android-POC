package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Option class used for displaying the multiple option choices for ach account linking.
 */


public class Option implements Parcelable {

    @SerializedName("guid")
    private String mGuid;

    @SerializedName("label")
    private String mLabel;

    @SerializedName("value")
    private String mValue;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mGuid);
        dest.writeString(this.mLabel);
        dest.writeString(this.mValue);
    }

    public Option(String guid, String label, String value) {
        mGuid = guid;
        mLabel = label;
        mValue = value;
    }

    private Option(Parcel in) {
        this.mGuid = in.readString();
        this.mLabel = in.readString();
        this.mValue = in.readString();
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel source) {
            return new Option(source);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}