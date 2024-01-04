package com.example.cf_sdk.defination.request;


import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Get mobile settings request parameter model class.
 */
public class SettingsParameter extends Parameters {

    @SerializedName("os")
    private String OS = "ANDR";

    @SerializedName("applicationID")
    private String applicationId = "20cbd0a0-fed3-407e-9be2-ba3825e6faaf";

    public SettingsParameter(Map<String, String> headers) {
        super(headers);
    }

    public String getOS(){
        return OS;
    }

    public String getApplicationId(){
        return applicationId;
    }

    public void setApplicationId(String appId){
        applicationId = appId;
    }
}
