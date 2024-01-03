package com.example.cf_sdk.changebankapi.model.account;

import com.google.gson.annotations.SerializedName;

public class ProductRelatedProfilesItem{

	@SerializedName("isDefault")
	private boolean isDefault=false;

	@SerializedName("profileDescription")
	private String profileDescription = "";

	@SerializedName("profileCode")
	private String profileCode = "";

	@SerializedName("profileIsVirtualCard")
	private boolean profileIsVirtualCard =false;

	@SerializedName("profileHasChipSupport")
	private boolean profileHasChipSupport =false;

	@SerializedName("profileCardPlasticCode")
	private String profileCardPlasticCode = "";

	@SerializedName("profileCardPlan")
	private String profileCardPlan = "";

	public void setIsDefault(boolean isDefault){
		this.isDefault = isDefault;
	}

	public boolean isIsDefault(){
		return isDefault;
	}

	public void setProfileDescription(String profileDescription){
		this.profileDescription = profileDescription;
	}

	public String getProfileDescription(){
		return profileDescription;
	}

	public void setProfileCode(String profileCode){
		this.profileCode = profileCode;
	}

	public String getProfileCode(){
		return profileCode;
	}

	public void setProfileIsVirtualCard(boolean profileIsVirtualCard){
		this.profileIsVirtualCard = profileIsVirtualCard;
	}

	public boolean isProfileIsVirtualCard(){
		return profileIsVirtualCard;
	}

	public void setProfileHasChipSupport(boolean profileHasChipSupport){
		this.profileHasChipSupport = profileHasChipSupport;
	}

	public boolean isProfileHasChipSupport(){
		return profileHasChipSupport;
	}

	public void setProfileCardPlasticCode(String profileCardPlasticCode){
		this.profileCardPlasticCode = profileCardPlasticCode;
	}

	public String getProfileCardPlasticCode(){
		return profileCardPlasticCode;
	}

	public void setProfileCardPlan(String profileCardPlan){
		this.profileCardPlan = profileCardPlan;
	}

	public String getProfileCardPlan(){
		return profileCardPlan;
	}
}