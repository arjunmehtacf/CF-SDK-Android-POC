package com.example.cf_sdk.logic.changebanksdk.parameter.authentication;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 *
 * Update password request parameters
 */

public class UpdatePasswordParameters extends Parameters {


    @SerializedName("oldPassword")
    private String mOldPassword;

    @SerializedName("password")
    private String mNewPassword;

    @SerializedName("username")
    private String username;

    private UpdatePasswordParameters(HashMap<String, String> headers, String oldPassword, String newPassword) {
        super(headers);
        mOldPassword = oldPassword;
        mNewPassword = newPassword;
    }


    public static UpdatePasswordParameters Create(String oldPassword, String newPassword) {
        return new UpdatePasswordParameters(new HashMap<String, String>(), oldPassword, newPassword);
    }


    public void setMemberId(String memberId) {
        username = memberId;
    }

    public String getMemberId() {
        return username;
    }

}
