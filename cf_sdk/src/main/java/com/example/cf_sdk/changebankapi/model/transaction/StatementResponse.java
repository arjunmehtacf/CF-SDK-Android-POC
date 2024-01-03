package com.example.cf_sdk.changebankapi.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class for statement api response.
 */
public class StatementResponse{

	@SerializedName("sequenceIndex")
	private Object sequenceIndex;

	@SerializedName("transactions")
	private List<TransactionsItem> transactions = null;

	@SerializedName("cardNumber")
	private String cardNumber = "";

	@SerializedName("cardToken")
	private String cardToken = "";

	public Object getSequenceIndex(){
		return sequenceIndex;
	}

	public List<TransactionsItem> getTransactions(){
		return transactions;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public String getCardToken(){
		return cardToken;
	}
}