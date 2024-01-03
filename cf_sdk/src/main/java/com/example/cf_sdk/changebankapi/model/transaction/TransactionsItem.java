package com.example.cf_sdk.changebankapi.model.transaction;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for transaction item
 */
public class TransactionsItem{

	@SerializedName("reference")
	private String reference = "";

	@SerializedName("product")
	private String product = "";

	@SerializedName("transDescription")
	private String transDescription = "";

	@SerializedName("transactionAmount")
	private TransactionAmount transactionAmount;

	@SerializedName("transactionDate")
	private String transactionDate = "";

	@SerializedName("postingTime")
	private String postingTime = "";

	@SerializedName("accountNbr")
	private String accountNbr = "";

	@SerializedName("type")
	private String type = "";

	@SerializedName("effectiveDate")
	private String effectiveDate = "";

	@SerializedName("cardToken")
	private String cardToken = "";

	@SerializedName("transactionId")
	private String transactionId = "";

	public String getReference(){
		return reference;
	}

	public String getProduct(){
		return product;
	}

	public String getTransDescription(){
		return transDescription;
	}

	public TransactionAmount getTransactionAmount(){
		return transactionAmount;
	}

	public String getTransactionDate(){
		return transactionDate;
	}

	public void setTransactionDate(String date){
		transactionDate = date;
	}

	public String getPostingTime(){
		return postingTime;
	}

	public String getAccountNbr(){
		return accountNbr;
	}

	public String getType(){
		return type;
	}

	public String getEffectiveDate(){
		return effectiveDate;
	}

	public String getCardToken(){
		return cardToken;
	}

	public String getTransactionId(){
		return transactionId;
	}
}