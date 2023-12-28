package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Enum for the Image Extension.
 */

public enum ImageExtension {

    @SerializedName("PDF")
    PDF("pdf"),

    @SerializedName("PNG")
    PNG("png"),

    @SerializedName("JPEG")
    JPEG("jpeg");

    String mExtensionName;

    ImageExtension(String extensionName) {
        mExtensionName = extensionName;
    }

    public String getExtensionName() {
        return mExtensionName;
    }

}
