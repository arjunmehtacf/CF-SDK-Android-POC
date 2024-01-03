
package com.example.cf_sdk.changebankapi.model.member.version;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alert {

    @SerializedName("show")
    @Expose
    private Boolean show;
    @SerializedName("message")
    @Expose
    private String message = "";
   /* @SerializedName("blocking")
    @Expose
    private Boolean blocking;*/

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public Boolean getBlocking() {
        return blocking;
    }

    public void setBlocking(Boolean blocking) {
        this.blocking = blocking;
    }*/

}
