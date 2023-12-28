package com.example.cf_sdk.logic.changebanksdk.model.transaction;

import com.google.gson.annotations.SerializedName;

/**
 * Response model class for transaction amount
 */
public class TransactionAmount{

	@SerializedName("amount")
	private String amount;

	@SerializedName("currencyCode")
	private String currencyCode;

	public String getAmount(){
		return amount;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}
}