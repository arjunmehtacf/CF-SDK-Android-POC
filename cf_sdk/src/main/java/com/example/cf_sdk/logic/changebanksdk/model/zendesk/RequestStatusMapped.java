package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gunveernatt on 6/4/18.
 */

public enum RequestStatusMapped {

    @SerializedName("new")
    New,
    @SerializedName("open")
    Open,
    @SerializedName("pending")
    Pending,
    @SerializedName("hold")
    Hold,
    @SerializedName("solved")
    Solved,
    @SerializedName("closed")
    Closed

}
