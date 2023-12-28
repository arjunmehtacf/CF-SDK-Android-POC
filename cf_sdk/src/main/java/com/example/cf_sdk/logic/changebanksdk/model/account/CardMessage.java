package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class CardMessage implements Parcelable {
    @SerializedName("defaultMessage")
    private String defaultMessage;

    @SerializedName("key")
    private String key;

    protected CardMessage(Parcel in) {
        defaultMessage = in.readString();
        key = in.readString();
    }

    public static final Creator<CardMessage> CREATOR = new Creator<CardMessage>() {
        @Override
        public CardMessage createFromParcel(Parcel in) {
            return new CardMessage(in);
        }

        @Override
        public CardMessage[] newArray(int size) {
            return new CardMessage[size];
        }
    };

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(defaultMessage);
        dest.writeString(key);
    }
}