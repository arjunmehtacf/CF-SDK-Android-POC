
package com.example.cf_sdk.logic.changebanksdk.model.member.version;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Inner model for version config class
 */
public class OptionalUpdate {

    @SerializedName("show")
    @Expose
    private Boolean show;
    @SerializedName("optionalVersion")
    @Expose
    private String optionalVersion;
    @SerializedName("message")
    @Expose
    private String message = "";

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getOptionalVersion() {
        return optionalVersion;
    }

    public void setOptionalVersion(String optionalVersion) {
        this.optionalVersion = optionalVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
