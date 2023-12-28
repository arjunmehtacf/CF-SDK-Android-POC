package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

public class CreateNewSubCardResponse{

	@SerializedName("expiryDate")
	private String expiryDate;

	@SerializedName("validFromDate")
	private String validFromDate;

	@SerializedName("cardToken")
	private String cardToken;

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setValidFromDate(String validFromDate) {
		this.validFromDate = validFromDate;
	}

	public void setCardToken(String cardToken) {
		this.cardToken = cardToken;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCardNumberMasked(String cardNumberMasked) {
		this.cardNumberMasked = cardNumberMasked;
	}

	@SerializedName("account")
	private String account;

	@SerializedName("cardNumberMasked")
	private String cardNumberMasked;

	public String getExpiryDate(){
		return expiryDate;
	}

	public String getValidFromDate(){
		return validFromDate;
	}

	public String getCardToken(){
		return cardToken;
	}

	public String getAccount(){
		return account;
	}

	public String getCardNumberMasked(){
		return cardNumberMasked;
	}
}