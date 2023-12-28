package com.example.cf_sdk.logic.changebanksdk.response;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Model of an error message from the API.
 */

class ResponseMessage {
    @SerializedName("key")
    private String mKey;

    @SerializedName("errorMessage")
    private String mDefaultMessage;

    String getDefaultMessage() {
        return mDefaultMessage;
    }

    String getMessageKey() {
        return mKey;
    }

    void setDefaultMessage(String defaultMessage){
        mDefaultMessage = defaultMessage;
    }

}
