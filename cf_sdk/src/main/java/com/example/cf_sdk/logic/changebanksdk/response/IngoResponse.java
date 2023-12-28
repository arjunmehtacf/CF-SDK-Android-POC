package com.example.cf_sdk.logic.changebanksdk.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model class for Ingo response.
 */
public class IngoResponse implements Serializable {

    @SerializedName("ingoCustomerId")
    private String ingoCustomerId;

    @SerializedName("ssoToken")
    private String ssoToken;

    @SerializedName("errorCode")
    public int errorCode;

    @SerializedName("statusCode")
    public int statusCode;


    public String getIngoCustomerId() {
        return ingoCustomerId;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public IngoResponse(String ingoCustomerId, String ssoToken) {
        this.ingoCustomerId = ingoCustomerId;
        this.ssoToken = ssoToken;
    }
}
