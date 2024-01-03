package com.example.cf_sdk.changebankapi.model.account;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class InstitutionCredential {

    @SerializedName("displayName")
    private String mDisplayName = "";

    @SerializedName("optionalMfa")
    private Boolean mOptionalMfa=false;

    @SerializedName("updatedAt")
    private String mUpdatedAt = "";

    @SerializedName("valueMask")
    private String mValueMask = "";

    @SerializedName("size")
    private Integer mSize=0;

    @SerializedName("guid")
    private String mGuid = "";

    @SerializedName("label")
    private String mLabel = "";

    @SerializedName("optional")
    private Boolean mOptional=false;

    @SerializedName("institutionGuid")
    private String mInstitutionGuid = "";

    @SerializedName("valueIdentifier")
    private String mValueIdentifier = "";

    @SerializedName("fieldTypeName")
    private String mFieldTypeName = "";

    @SerializedName("options")
    private List<Option> mOptions = null;

    @SerializedName("fieldName")
    private String mFieldName = "";

    @SerializedName("mfa")
    private Boolean mMfa=false;

    @SerializedName("createdAt")
    private String mCreatedAt = "";

    @SerializedName("escaped")
    private String mEscaped = "";

    @SerializedName("answerFieldType")
    private String mAnswerFieldType = "";

    @SerializedName("fieldType")
    private Integer mFieldType=0;

    @SerializedName("displayOrder")
    private Integer mDisplayOrder=0;

    @SerializedName("metaData")
    private String mMetaData = "";

    @SerializedName("maxLength")
    private Integer mMaxLength=0;

    @SerializedName("editable")
    private Boolean mEditable=false;

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
    }

    public Boolean getOptionalMfa() {
        return mOptionalMfa;
    }

    public void setOptionalMfa(Boolean optionalMfa) {
        this.mOptionalMfa = optionalMfa;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.mUpdatedAt = updatedAt;
    }

    public String getValueMask() {
        return mValueMask;
    }

    public void setValueMask(String valueMask) {
        this.mValueMask = valueMask;
    }

    public Integer getSize() {
        return mSize;
    }

    public void setSize(Integer size) {
        this.mSize = size;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String guid) {
        this.mGuid = guid;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }

    public Boolean getOptional() {
        return mOptional;
    }

    public void setOptional(Boolean optional) {
        this.mOptional = optional;
    }

    public String getInstitutionGuid() {
        return mInstitutionGuid;
    }

    public void setInstitutionGuid(String institutionGuid) {
        this.mInstitutionGuid = institutionGuid;
    }

    public String getValueIdentifier() {
        return mValueIdentifier;
    }

    public void setValueIdentifier(String valueIdentifier) {
        this.mValueIdentifier = valueIdentifier;
    }

    public String getFieldTypeName() {
        return mFieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.mFieldTypeName = fieldTypeName;
    }

    public List<Option> getOptions() {
        return mOptions;
    }

    public void setOptions(List<Option> options) {
        this.mOptions = options;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public void setFieldName(String fieldName) {
        this.mFieldName = fieldName;
    }

    public Boolean getMfa() {
        return mMfa;
    }

    public void setMfa(Boolean mfa) {
        this.mMfa = mfa;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.mCreatedAt = createdAt;
    }

    public String getEscaped() {
        return mEscaped;
    }

    public void setEscaped(String escaped) {
        this.mEscaped = escaped;
    }

    public String getAnswerFieldType() {
        return mAnswerFieldType;
    }

    public void setAnswerFieldType(String answerFieldType) {
        this.mAnswerFieldType = answerFieldType;
    }

    public Integer getFieldType() {
        return mFieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.mFieldType = fieldType;
    }

    public Integer getDisplayOrder() {
        return mDisplayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.mDisplayOrder = displayOrder;
    }

    public String getMetaData() {
        return mMetaData;
    }

    public void setMetaData(String metaData) {
        this.mMetaData = metaData;
    }

    public Integer getMaxLength() {
        return mMaxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.mMaxLength = maxLength;
    }

    public Boolean getEditable() {
        return mEditable;
    }

    public void setEditable(Boolean editable) {
        this.mEditable = editable;
    }

}