package com.example.cf_sdk.changebankapi.parameter.authentication;

import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.util.SecurityUtils;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Parameters used to update member credentials.
 */

public class UpdateCredentialsParameters extends Parameters {

    private transient byte[] mSalt;
    private transient long mMemberId;
    private transient String mPin;

    @SerializedName("username")
    private final String mUsername;

    @SerializedName("password")
    private final String mPassword;

    @SerializedName("pin")
    private final String mHashedPin;

    @SerializedName("biometric")
    private final String mBiometric;

    public static UpdateCredentialsParameters createUpdateUsername(
            String username) {
        return new UpdateCredentialsParameters(new HashMap<String, String>(), 0, username, null, null, null, null, null);
    }

    public static UpdateCredentialsParameters createUpdatePin(
            String pin) {
        return new UpdateCredentialsParameters(new HashMap<String, String>(), 0, null, null, null, pin, null, null);
    }


    public static UpdateCredentialsParameters createUpdateBiometric() {
        String hashedFingerprintPassword = SecurityUtils.createFingerprintHash("FINGERPRINT_AUTH");
        return new UpdateCredentialsParameters(new HashMap<String, String>(), 0, null, null, null, null, null, hashedFingerprintPassword);
    }


    public UpdateCredentialsParameters withHashedPin() {
        // Hashing pin.
        byte[] salt = SecurityUtils.INSTANCE.generateRandomSalt();
        String hashedPin = SecurityUtils.INSTANCE.createHashedPassword(mPin.toCharArray(), salt);
        return new UpdateCredentialsParameters(getHeaders(), mMemberId, mUsername, mPassword, hashedPin, mPin, salt, mBiometric);
    }

    private UpdateCredentialsParameters(Map<String, String> headers,
                                        long memberId,
                                        String username,
                                        String password,
                                        String hashedPin,
                                        String pin,
                                        byte[] salt, String biometric) {
        super(headers);

        mMemberId = memberId;
        mUsername = username;
        mPassword = password;
        mHashedPin = hashedPin;
        mPin = pin;
        mSalt = salt;
        mBiometric = biometric;
    }

    public byte[] getSalt() {
        return mSalt;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public String getPin() {
        return mPin;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getBiometric() {
        return mBiometric;
    }

}
