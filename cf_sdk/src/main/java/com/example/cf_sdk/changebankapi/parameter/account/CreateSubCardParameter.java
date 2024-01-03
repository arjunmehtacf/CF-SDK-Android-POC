package com.example.cf_sdk.changebankapi.parameter.account;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Create sub card api request parameter model class.
 */
public class CreateSubCardParameter extends Parameters {
    @SerializedName("cardProfile")
    private String cardProfile;


    @SerializedName("cardNumber")
    private String cardNumber;

    @SerializedName("embossName")
    private String embossName;

    public String getMainCardNumber() {
        return cardNumber;
    }

    public void setMainCardNumber(String mainCardNumber) {
        this.cardNumber = mainCardNumber;
    }

    public String getEmbossName() {
        return embossName;
    }

    public void setEmbossName(String embossName) {
        this.embossName = embossName;
    }

    public CreateSubCardParameter(Map<String, String> headers) {
        super(headers);
    }

    public String getCardProduct() {
        return cardProfile;
    }

    public void setCardProduct(String cardProduct) {
        this.cardProfile = cardProduct;
    }

    public static CreateSubCardParameter create() {
        return new CreateSubCardParameter(new HashMap<String, String>());
    }
}
