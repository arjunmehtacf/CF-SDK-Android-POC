package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for RSAPublic Key
 */
public class RSAPublicKeyResponse {

    @SerializedName("publicKeyX509")
    private  String publicKeyX509;

    public String getPublicKeyX509() {
        return publicKeyX509;
    }

    public void setPublicKeyX509(String publicKeyX509) {
        this.publicKeyX509 = publicKeyX509;
    }

    public String getKeyExpiryDate() {
        return keyExpiryDate;
    }

    public void setKeyExpiryDate(String keyExpiryDate) {
        this.keyExpiryDate = keyExpiryDate;
    }

    @SerializedName("keyExpiryDate")
    private  String keyExpiryDate;
}
