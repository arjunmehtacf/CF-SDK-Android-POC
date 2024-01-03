package com.example.cf_sdk.changebankapi.parameter.member;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Card details request parameter model class.
 */
public class CardDetailParameter extends Parameters {

    public static CardDetailParameter create() {
        return new CardDetailParameter(new HashMap<String, String>());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @SerializedName("cardNumber")
    private String cardNumber="";


    public CardDetailParameter(Map<String, String> headers) {
        super(headers);
    }
}
