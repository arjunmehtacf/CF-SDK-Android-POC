package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Activate card request parameter model class.
 */
public class ActivateCardParameter extends Parameters {

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    @SerializedName("cvv2")
    private String cvv2;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @SerializedName("cardNumber")
    private String cardNumber;

    public static ActivateCardParameter create() {
        return new ActivateCardParameter(new HashMap<String, String>());
    }

    public ActivateCardParameter(Map<String, String> headers) {
        super(headers);
    }

}
