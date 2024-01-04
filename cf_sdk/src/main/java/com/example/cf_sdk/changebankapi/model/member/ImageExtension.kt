package com.example.cf_sdk.changebankapi.model.member

import com.google.gson.annotations.SerializedName

/**
 *
 * Enum for the Image Extension.
 */
enum class ImageExtension(extensionName: String) {
    @SerializedName("PDF")
    PDF("pdf"), @SerializedName("PNG")
    PNG("png"), @SerializedName("JPEG")
    JPEG("jpeg");

    var extensionName = ""

    init {
        this.extensionName = extensionName
    }
}