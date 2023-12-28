package com.example.cf_sdk.logic.changebanksdk.parameter.authentication;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.util.SecurityUtils;
import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles login information to authenticate.
 */

public class LoginParameters extends Parameters {


    @SerializedName("mpin")
    private final String mPin;

    @SerializedName("profilingSessionId")
    private final String mThreatMetrixSessionId;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("biometric")
    private String mBiometric;

    @SerializedName("password")
    private final String mPassword;

    @SerializedName("deviceId")
    private final String mInstallationId;

    @SerializedName("pin")
    private String mHashedPin;

    private transient boolean mShouldSaveUsername;

    private transient LoginMethodType mLoginMethod;

    public enum LoginMethodType {
        UNKNOWN,
        PASSWORD_LOGIN,
        PIN_LOGIN,
        FINGERPRINT_LOGIN;
    }

    public static LoginParameters createWithPassword(
            String username,
            String password,
            String installationId,
            Boolean shouldSaveUsername,
            String threatMetrixSessionId) throws UnsupportedEncodingException {
        return new LoginParameters(new HashMap<String, String>(), username, password, null, installationId, null, shouldSaveUsername, threatMetrixSessionId, LoginMethodType.PASSWORD_LOGIN);
    }


    public static LoginParameters createWithPin(
            String pin,
            String installationId,
            String threatMetrixSessionId) throws UnsupportedEncodingException {

        return new LoginParameters(new HashMap<String, String>(), null, null, pin, installationId, null, true, threatMetrixSessionId, LoginMethodType.PIN_LOGIN);
    }


    public static LoginParameters createWithBiometric(
            String username,
            String biometric,
            String installationId,
            String threatMetrixSessionId) throws UnsupportedEncodingException {

        return new LoginParameters(new HashMap<String, String>(), username, null, null, installationId, biometric, true, threatMetrixSessionId, LoginMethodType.FINGERPRINT_LOGIN);
    }

    private LoginParameters(
            Map<String, String> headers,
            String username,
            String password,
            String pin,
            String installationId,
            String biometric,
            Boolean shouldSaveUsername,
            String threatMetrixSessionId,
            LoginMethodType loginMethodType) throws UnsupportedEncodingException {
        super(headers);

        mInstallationId = installationId;
        mUsername = username != null ? username.toLowerCase() : null;
        mPassword = password;
        mPin = pin;
        mBiometric = biometric;
        mShouldSaveUsername = shouldSaveUsername;
        mThreatMetrixSessionId = threatMetrixSessionId;
        mLoginMethod = loginMethodType;
    }

    public LoginMethodType getLoginMethod() {
        return mLoginMethod;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getBiometric() {
        return mBiometric;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getInstallationId() {
        return mInstallationId;
    }

    public String getPin() {
        return mPin;
    }

    public String getHashedPin() {
        return mHashedPin;
    }

    public boolean getShouldSaveUsername() {
        return mShouldSaveUsername;
    }

    public void generateHashedPin(byte[] salt) {
        mHashedPin = SecurityUtils.createHashedPassword(mPin.toCharArray(), salt);
    }

    public boolean isLoginWithPin() {
        return mPin != null;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }
}
