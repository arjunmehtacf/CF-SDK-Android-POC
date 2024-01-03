package com.example.cf_sdk.changebankapi.model.account;

import com.google.gson.annotations.SerializedName;

public class ProductAccountStyle{

	@SerializedName("accountStyle")
	private String accountStyle = "";

	public void setAccountStyle(String accountStyle){
		this.accountStyle = accountStyle;
	}

	public String getAccountStyle(){
		return accountStyle;
	}
}