package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Contains parameters to attempt a Card Activation.
 */

public class CardActivationParameters extends Parameters {
    @SerializedName("pinOrBlock")
    private String pinOrBlock;

    @SerializedName("pinLength")
    private String pinLength;

    public String getEncryptedPin() {
        return encryptedPin;
    }

    public void setEncryptedPin(String encryptedPin) {
        this.encryptedPin = encryptedPin;
    }

    @SerializedName("encryptedPin")
    private String encryptedPin;

    @SerializedName("cardNumber")
    private String cardNumber;


    public static CardActivationParameters create(Map<String, String> headers,
                                                  String dob,
                                                  String last4ssn,
                                                  String pan) {
        return new CardActivationParameters(headers, dob, last4ssn, pan);
    }

    private CardActivationParameters(Map<String, String> headers, String length, String pin, String encPin) {
        super(headers);

        pinLength = length;
        pinOrBlock = pin;
        encryptedPin = encPin;
    }

    public String getCardId() {
        return cardNumber;
    }

    public void setCardId(String cardToken) {
        cardNumber = cardToken;
    }
}
