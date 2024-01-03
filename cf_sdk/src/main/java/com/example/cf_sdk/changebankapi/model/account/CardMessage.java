package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class CardMessage {
    @SerializedName("defaultMessage")
    private String defaultMessage = "";

    @SerializedName("key")
    private String key = "";


    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getKey() {
        return key;
    }


}