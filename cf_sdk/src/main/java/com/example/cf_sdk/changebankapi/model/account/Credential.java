package com.example.cf_sdk.changebankapi.model.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * The Credential object that describes what credentials are needed for ach linking.
 */

public class Credential {
    @SerializedName("guid")
    private String mGuid = "";

    @SerializedName("credentialGuid")
    private String mCredentialGuid = "";

    @SerializedName("displayName")
    private String mDisplayName = "";

    @SerializedName("updatedAt")
    private String mUpdatedAt = "";

    @SerializedName("valueMask")
    private String mValueMask = "";

    @SerializedName("size")
    private Integer mSize = 0;

    @SerializedName("label")
    private String mLabel = "";

    @SerializedName("optional")
    private Boolean mOptional=false;

    @SerializedName("institutionGuid")
    private String mInstitutionGuid = "";

    @SerializedName("valueIdentifier")
    private String mValueIdentifier = "";

    @SerializedName("fieldTypeName")
    private CredentialType mFieldTypeName;

    @SerializedName("options")
    private List<Option> mOptions = null;

    @SerializedName("fieldName")
    private String mFieldName = "";

    @SerializedName("value")
    private String mValue = "";

    @SerializedName("mfa")
    private Boolean mMfa=false;

    @SerializedName("optionalMfa")
    private Boolean mOptionalMfa=false;

    @SerializedName("createdAt")
    private String mCreatedAt = "";

    @SerializedName("escaped")
    private String mEscaped = "";

    @SerializedName("answerFieldType")
    private String mAnswerFieldType = "";

    @SerializedName("fieldType")
    private Integer mFieldType = 0;

    @SerializedName("displayOrder")
    private Integer mDisplayOrder = 0;

    @SerializedName("metaData")
    private String mMetaData = "";

    @SerializedName("maxLength")
    private Integer mMaxLength = 0;

    @SerializedName("editable")
    private Boolean mEditable=false;

    public static Credential createForLogin(
            String guid,
            String institutionGuid,
            String displayName,
            Integer displayOrder,
            String label) {
        return create(
                guid,
                institutionGuid,
                displayName,
                displayOrder,
                label,
                false,
                false,
                DateTime.now().minusYears(1).toString(),
                DateTime.now().minusYears(1).toString(),
                "",
                0,
                false,
                "",
                CredentialType.LOGIN,
                "LOGIN",
                "",
                "",
                3,
                "",
                0,
                true,
                new ArrayList<Option>());
    }

    public static Credential createForPassword(
            String guid,
            String institutionGuid,
            String displayName,
            Integer displayOrder,
            String label) {
        return create(
                guid,
                institutionGuid,
                displayName,
                displayOrder,
                label,
                false,
                false,
                DateTime.now().minusYears(1).toString(),
                DateTime.now().minusYears(1).toString(),
                "",
                0,
                false,
                "",
                CredentialType.PASSWORD,
                "PASSWORD",
                "",
                "",
                1,
                "",
                0,
                true,
                new ArrayList<Option>());
    }

    public static Credential createForOptions(
            String guid,
            String institutionGuid,
            String displayName,
            Integer displayOrder,
            String label,
            List<Option> options) {
        return create(
                guid,
                institutionGuid,
                displayName,
                displayOrder,
                label,
                false,
                true,
                DateTime.now().minusYears(1).toString(),
                DateTime.now().minusYears(1).toString(),
                "",
                0,
                false,
                "",
                CredentialType.OPTIONS,
                "",
                "",
                "",
                2,
                "",
                0,
                false,
                options);
    }

    public static Credential createForChallenge(
            String guid,
            String institutionGuid,
            String label) {

        return create(
                guid,
                institutionGuid,
                null,
                null,
                label,
                false,
                true,
                DateTime.now().minusYears(1).toString(),
                DateTime.now().minusYears(1).toString(),
                null,
                null,
                false,
                "",
                CredentialType.TEXT,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                new ArrayList<Option>());
    }

    public static Credential createForImage(
            String guid,
            String institutionGuid,
            String displayName,
            Integer displayOrder,
            String label,
            String metadata) {
        return create(
                guid,
                institutionGuid,
                displayName,
                displayOrder,
                label,
                false,
                true,
                DateTime.now().minusYears(1).toString(),
                DateTime.now().minusYears(1).toString(),
                "",
                0,
                false,
                "",
                CredentialType.IMAGE_DATA,
                "PASSWORD",
                "",
                "",
                13,
                metadata,
                0,
                true,
                new ArrayList<Option>());
    }

    public static Credential create(String guid,
                                    String institutionGuid,
                                    String displayName,
                                    Integer displayOrder,
                                    String label,
                                    boolean optionalMfa,
                                    boolean mfa,
                                    String createdAt,
                                    String updatedAt,
                                    String valueMask,
                                    Integer size,
                                    boolean optional,
                                    String valueIdentifier,
                                    CredentialType fieldTypeName,
                                    String fieldName,
                                    String escaped,
                                    String answerFieldType,
                                    Integer fieldType,
                                    String metaData,
                                    Integer maxLength,
                                    boolean editable,
                                    List<Option> options) {
        return new Credential(
                guid,
                institutionGuid,
                displayName,
                displayOrder,
                label,
                optionalMfa,
                mfa,
                createdAt,
                updatedAt,
                valueMask,
                size,
                optional,
                valueIdentifier,
                fieldTypeName,
                fieldName,
                escaped,
                answerFieldType,
                fieldType,
                metaData,
                maxLength,
                editable,
                options);
    }

    public Credential(String guid,
                      String institutionGuid,
                      String displayName,
                      Integer displayOrder,
                      String label,
                      boolean optionalMfa,
                      boolean mfa,
                      String createdAt,
                      String updatedAt,
                      String valueMask,
                      Integer size,
                      boolean optional,
                      String valueIdentifier,
                      CredentialType fieldTypeName,
                      String fieldName,
                      String escaped,
                      String answerFieldType,
                      Integer fieldType,
                      String metaData,
                      Integer maxLength,
                      boolean editable,
                      List<Option> options) {
        mDisplayName = displayName;
        mOptionalMfa = optionalMfa;
        mUpdatedAt = updatedAt;
        mValueMask = valueMask;
        mSize = size;
        mGuid = guid;
        mLabel = label;
        mOptional = optional;
        mInstitutionGuid = institutionGuid;
        mValueIdentifier = valueIdentifier;
        mFieldTypeName = fieldTypeName;
        mOptions = options;
        mFieldName = fieldName;
        mMfa = mfa;
        mCreatedAt = createdAt;
        mEscaped = escaped;
        mAnswerFieldType = answerFieldType;
        mFieldType = fieldType;
        mDisplayOrder = displayOrder;
        mMetaData = metaData;
        mMaxLength = maxLength;
        mEditable = editable;
    }

    public Credential(String value, String guid, CredentialType credentialType) {
        mValue = value;
        mCredentialGuid = guid;
        mFieldTypeName = credentialType;
    }

    public Credential withValue(String value) {
        return new Credential(value, mGuid, mFieldTypeName);
    }

    public String getDisplayName() {
        return mDisplayName;
    }


    public Boolean getOptionalMfa() {
        return mOptionalMfa;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getValueMask() {
        return mValueMask;
    }

    public Integer getSize() {
        return mSize;
    }

    public String getGuid() {
        return mGuid;
    }

    public String getLabel() {
        return mLabel;
    }

    public boolean isPassword() {
        return mFieldTypeName == CredentialType.PASSWORD;
    }

    public Boolean getOptional() {
        return mOptional;
    }

    public String getInstitutionGuid() {
        return mInstitutionGuid;
    }

    public String getValueIdentifier() {
        return mValueIdentifier;
    }

    public CredentialType getFieldTypeName() {
        return mFieldTypeName;
    }

    public List<Option> getOptions() {
        return mOptions;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public Boolean getMfa() {
        return mMfa;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getEscaped() {
        return mEscaped;
    }

    public String getAnswerFieldType() {
        return mAnswerFieldType;
    }

    public Integer getFieldType() {
        return mFieldType;
    }

    public Integer getDisplayOrder() {
        return mDisplayOrder == null ? 0 : mDisplayOrder;
    }

    public String getMetaData() {
        return mMetaData;
    }

    public Integer getMaxLength() {
        return mMaxLength;
    }

    public Boolean getEditable() {
        return mEditable;
    }

    public String getValue() {
        return mValue;
    }

}
