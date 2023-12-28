package com.example.cf_sdk.logic.changebanksdk.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CardToCardResponse implements Parcelable {
    @SerializedName("messages")
    List<CardMessage> messages;

    protected CardToCardResponse(Parcel in) {
        messages = in.createTypedArrayList(CardMessage.CREATOR);
    }

    public static final Creator<CardToCardResponse> CREATOR = new Creator<CardToCardResponse>() {
        @Override
        public CardToCardResponse createFromParcel(Parcel in) {
            return new CardToCardResponse(in);
        }

        @Override
        public CardToCardResponse[] newArray(int size) {
            return new CardToCardResponse[size];
        }
    };

    public List<CardMessage> getMessages() {
        return messages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(messages);
    }
}



