package com.example.cf_sdk.changebankapi.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for activate card
 */
public class ActivateCardResponse {

    public String getCardNumberMasked() {
        return cardNumberMasked;
    }

    public void setCardNumberMasked(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    @SerializedName("cardNumberMasked")
    private String cardNumberMasked;
    @SerializedName("cardToken")
    private String cardToken;
}
