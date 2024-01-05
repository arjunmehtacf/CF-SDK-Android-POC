package com.example.cf_sdk.changebankapi.fingerprint;


import com.example.cf_sdk.changebankapi.model.FingerprintResponse;

/**
 *
 * Interface for interacting with the fingerprint library/implementation.
 */

public interface FingerprintHelper {

    FingerprintResponse readFingerprint(String username);

    void writeFingerprint(String username, String passwordHash);

    boolean canStoreFingerprintSecurely();

    void clearAllFingerprint();

    void clearSpecificFingerprint(String username);

    boolean isUserFingerprintSaved(String username);

}

