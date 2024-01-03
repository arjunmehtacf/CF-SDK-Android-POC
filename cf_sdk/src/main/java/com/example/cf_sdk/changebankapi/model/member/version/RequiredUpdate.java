
package com.example.cf_sdk.changebankapi.model.member.version;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response model class for required update.
 */
public class RequiredUpdate {

    @SerializedName("show")
    @Expose
    private Boolean show;
    @SerializedName("minimumVersion")
    @Expose
    private String minimumVersion;
    @SerializedName("message")
    @Expose
    private String message = " ";

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getMinimumVersion() {
        return minimumVersion;
    }

    public void setMinimumVersion(String minimumVersion) {
        this.minimumVersion = minimumVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
