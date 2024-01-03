package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CardToCardResponse {
    @SerializedName("messages")
    List<CardMessage> messages;

    public List<CardMessage> getMessages() {
        return messages;
    }

}



