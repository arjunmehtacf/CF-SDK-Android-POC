package com.example.cf_sdk.logic.changebanksdk.model.account;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Credential types.
 */

public enum CredentialType {
    @SerializedName("LOGIN")
    LOGIN,

    @SerializedName("PASSWORD")
    PASSWORD,

    @SerializedName("TEXT")
    TEXT,

    @SerializedName("OPTIONS")
    OPTIONS,

    @SerializedName("IMAGE_DATA")
    IMAGE_DATA,
}
