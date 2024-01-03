package com.example.cf_sdk.changebankapi.model.member;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for card details
 */
public class CardDetailResponse{

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setCardNbrEnc(String cardNbrEnc) {
		this.cardNbrEnc = cardNbrEnc;
	}

	public void setCardCVV2Enc(String cardCVV2Enc) {
		this.cardCVV2Enc = cardCVV2Enc;
	}

	@SerializedName("expiryDate")
	private String expiryDate;

	@SerializedName("cardNbrEnc")
	private String cardNbrEnc;

	@SerializedName("cardCVV2Enc")
	private String cardCVV2Enc;

	public String getExpiryDate(){
		return expiryDate;
	}

	public String getCardNbrEnc(){
		return cardNbrEnc;
	}

	public String getCardCVV2Enc(){
		return cardCVV2Enc;
	}
}