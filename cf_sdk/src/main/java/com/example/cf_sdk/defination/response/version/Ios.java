
package com.example.cf_sdk.defination.response.version;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ios {

    @SerializedName("alert")
    @Expose
    private Alert alert;
    @SerializedName("optionalUpdate")
    @Expose
    private OptionalUpdate optionalUpdate;
    @SerializedName("requiredUpdate")
    @Expose
    private RequiredUpdate requiredUpdate;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public OptionalUpdate getOptionalUpdate() {
        return optionalUpdate;
    }

    public void setOptionalUpdate(OptionalUpdate optionalUpdate) {
        this.optionalUpdate = optionalUpdate;
    }

    public RequiredUpdate getRequiredUpdate() {
        return requiredUpdate;
    }

    public void setRequiredUpdate(RequiredUpdate requiredUpdate) {
        this.requiredUpdate = requiredUpdate;
    }

}
