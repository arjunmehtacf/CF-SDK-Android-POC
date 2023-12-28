package com.example.cf_sdk.logic.changebanksdk.model.transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Main model class for transaction response
 */

public class TransactionResponse{

	@SerializedName("sequenceIndex")
	private Object sequenceIndex;

	@SerializedName("declinedCount")
	private int declinedCount;

	@SerializedName("authorisations")
	private List<Transaction> authorisations;

	@SerializedName("outstandingCount")
	private int outstandingCount;

	@SerializedName("approvedCount")
	private int approvedCount;

	@SerializedName("cardNumberMasked")
	private String cardNumberMasked;

	public void setSequenceIndex(Object sequenceIndex){
		this.sequenceIndex = sequenceIndex;
	}

	public Object getSequenceIndex(){
		return sequenceIndex;
	}

	public void setDeclinedCount(int declinedCount){
		this.declinedCount = declinedCount;
	}

	public int getDeclinedCount(){
		return declinedCount;
	}

	public void setAuthorisations(List<Transaction> authorisations){
		this.authorisations = authorisations;
	}

	public List<Transaction> getAuthorisations(){
		return authorisations;
	}

	public void setOutstandingCount(int outstandingCount){
		this.outstandingCount = outstandingCount;
	}

	public int getOutstandingCount(){
		return outstandingCount;
	}

	public void setApprovedCount(int approvedCount){
		this.approvedCount = approvedCount;
	}

	public int getApprovedCount(){
		return approvedCount;
	}

	public void setCardNumberMasked(String cardNumberMasked){
		this.cardNumberMasked = cardNumberMasked;
	}

	public String getCardNumberMasked(){
		return cardNumberMasked;
	}

	public static TransactionResponse create(String id) {
		return new TransactionResponse(id);
	}

	private TransactionResponse(String id) {
		this.sequenceIndex = id;
	}
}