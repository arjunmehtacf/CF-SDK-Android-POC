package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters for reset password endpoint.
 */

public class ResetPasswordParameters extends Parameters {

    public String getmUsername() {
        return mUsername;
    }

    @SerializedName("username")
    private String mUsername;

    @SerializedName("mfaCode")
    private String mMfaCode;

    @SerializedName("password")
    private String mPassword;

    public boolean isIsrememberusername() {
        return isrememberusername;
    }

    public void setIsrememberusername(boolean isrememberusername) {
        this.isrememberusername = isrememberusername;
    }

    private boolean isrememberusername;


    public static ResetPasswordParameters create(String mfaCode,
                                                 String username,
                                                 String password,boolean isrememberusername) {
        return new ResetPasswordParameters(
                new HashMap<String, String>(),
                mfaCode,
                username,
                password,isrememberusername);
    }

    private ResetPasswordParameters(Map<String, String> headers,
                                    String mfaCode,
                                    String username,
                                    String password,boolean isremember) {
        super(headers);
        mMfaCode = mfaCode;
        mUsername = username;
        mPassword = password;
        isrememberusername =isremember;
    }


    public ResetPasswordParameters withTokenAndMemberId(String token,
                                                        String memberId) {
        return new ResetPasswordParameters(
                getHeaders(),
                mMfaCode,
                mUsername,
                mPassword,isrememberusername);
    }

}
