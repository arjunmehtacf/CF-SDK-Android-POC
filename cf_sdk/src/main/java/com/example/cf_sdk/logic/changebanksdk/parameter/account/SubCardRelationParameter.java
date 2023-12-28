package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Sub card relation API request parameter model class.
 */
public class SubCardRelationParameter extends Parameters {

    @SerializedName("productCode")
    private String productCode;

    public SubCardRelationParameter(Map<String, String> headers) {
        super(headers);
    }

    public String getCardProduct() {
        return productCode;
    }

    public void setCardProduct(String cardProduct) {
        this.productCode = cardProduct;
    }

    public static SubCardRelationParameter create() {
        return new SubCardRelationParameter(new HashMap<String, String>());
    }
}
