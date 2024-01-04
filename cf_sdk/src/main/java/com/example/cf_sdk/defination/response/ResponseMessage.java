package com.example.cf_sdk.defination.response;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Model of an error message from the API.
 */

public class ResponseMessage {
    @SerializedName("key")
    private String mKey;

    @SerializedName("errorMessage")
    private String mDefaultMessage;

    public String getDefaultMessage() {
        return mDefaultMessage;
    }

    public String getMessageKey() {
        return mKey;
    }

    public void setDefaultMessage(String defaultMessage){
        mDefaultMessage = defaultMessage;
    }

}
