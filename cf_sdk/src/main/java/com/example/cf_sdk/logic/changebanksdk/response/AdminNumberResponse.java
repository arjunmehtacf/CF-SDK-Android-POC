package com.example.cf_sdk.logic.changebanksdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for admin number response.
 */
public class AdminNumberResponse {
    @SerializedName("showBalanceTransferOption")
    private Boolean showBalanceTransferOption;

    public AdminNumberResponse(boolean showBalanceTransferOption) {
        this.showBalanceTransferOption = showBalanceTransferOption;
    }

    public static AdminNumberResponse Create(boolean showBalanceTransferOption) {
        return new AdminNumberResponse(showBalanceTransferOption);
    }

    public Boolean getShowBalanceTransferOption() {
        return showBalanceTransferOption;
    }
}
