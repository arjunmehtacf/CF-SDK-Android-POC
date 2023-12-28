package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsReponseItem{

	@SerializedName("productIsCorporateType")
	private boolean productIsCorporateType;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("productRelatedProfiles")
	private List<ProductRelatedProfilesItem> productRelatedProfiles;

	@SerializedName("productCurrency")
	private String productCurrency;

	@SerializedName("productAccountStyle")
	private ProductAccountStyle productAccountStyle;

	@SerializedName("allowSubcards")
	private boolean allowSubcards;

	@SerializedName("productDefaultProfile")
	private String productDefaultProfile;

	@SerializedName("productDescription")
	private String productDescription;

	@SerializedName("productIsBusinessType")
	private boolean productIsBusinessType;

	public void setProductIsCorporateType(boolean productIsCorporateType){
		this.productIsCorporateType = productIsCorporateType;
	}

	public boolean isProductIsCorporateType(){
		return productIsCorporateType;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setProductRelatedProfiles(List<ProductRelatedProfilesItem> productRelatedProfiles){
		this.productRelatedProfiles = productRelatedProfiles;
	}

	public List<ProductRelatedProfilesItem> getProductRelatedProfiles(){
		return productRelatedProfiles;
	}

	public void setProductCurrency(String productCurrency){
		this.productCurrency = productCurrency;
	}

	public String getProductCurrency(){
		return productCurrency;
	}

	public void setProductAccountStyle(ProductAccountStyle productAccountStyle){
		this.productAccountStyle = productAccountStyle;
	}

	public ProductAccountStyle getProductAccountStyle(){
		return productAccountStyle;
	}

	public void setAllowSubcards(boolean allowSubcards){
		this.allowSubcards = allowSubcards;
	}

	public boolean isAllowSubcards(){
		return allowSubcards;
	}

	public void setProductDefaultProfile(String productDefaultProfile){
		this.productDefaultProfile = productDefaultProfile;
	}

	public String getProductDefaultProfile(){
		return productDefaultProfile;
	}

	public void setProductDescription(String productDescription){
		this.productDescription = productDescription;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public void setProductIsBusinessType(boolean productIsBusinessType){
		this.productIsBusinessType = productIsBusinessType;
	}

	public boolean isProductIsBusinessType(){
		return productIsBusinessType;
	}
}