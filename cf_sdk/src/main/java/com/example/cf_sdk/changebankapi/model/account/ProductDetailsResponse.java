package com.example.cf_sdk.changebankapi.model.account;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Main response model class of Product Details
 */
public class ProductDetailsResponse {

	@SerializedName("ProductDetailsReponse")
	private List<ProductDetailsReponseItem> productDetailsReponse;

	public void setProductDetailsReponse(List<ProductDetailsReponseItem> productDetailsReponse){
		this.productDetailsReponse = productDetailsReponse;
	}

	public List<ProductDetailsReponseItem> getProductDetailsReponse(){
		return productDetailsReponse;
	}
}