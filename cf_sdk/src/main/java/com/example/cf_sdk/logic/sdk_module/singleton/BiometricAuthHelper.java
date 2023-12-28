package com.changefinancials.changebank.fingerprint;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.biometric.BiometricManager;

import com.example.cf_sdk.logic.changebanksdk.fingerprint.FingerprintHelper;
import com.example.cf_sdk.logic.changebanksdk.model.FingerprintResponse;


/**
 *
 * Whorlwind implementation of FingerprintHelper
 */

public class BiometricAuthHelper implements FingerprintHelper {

    private final SharedPreferences mSharedPreferencesStorage;
    private final BiometricManager mBiometricManager;

    public BiometricAuthHelper(BiometricManager biometricManager, SharedPreferences sharedPreferencesStorage) {
        mBiometricManager = biometricManager;
        mSharedPreferencesStorage = sharedPreferencesStorage;
    }

    @Override
    public FingerprintResponse readFingerprint(String username) {
        FingerprintResponse fingerprintResponse = null;
        String value = mSharedPreferencesStorage.getString(username, null);

        switch (mBiometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                fingerprintResponse = new FingerprintResponse(
                        FingerprintResponse.ReadState.BIOMETRIC_SUCCESS,
                        "App can authenticate using biometrics.",
                        value);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "No biometric features available on this device.");
                fingerprintResponse = new FingerprintResponse(
                        FingerprintResponse.ReadState.BIOMETRIC_ERROR_NO_HARDWARE,
                        "No biometric features available on this device.",
                        null);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                fingerprintResponse = new FingerprintResponse(
                        FingerprintResponse.ReadState.BIOMETRIC_ERROR_HW_UNAVAILABLE,
                        "Biometric features are currently unavailable.",
                        null);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("MY_APP_TAG", "The user hasn't associated " +
                        "any biometric credentials with their account.");
                fingerprintResponse = new FingerprintResponse(
                        FingerprintResponse.ReadState.BIOMETRIC_ERROR_NONE_ENROLLED,
                        "The user hasn't associated any biometric credentials with their account.",
                        null);
                break;
        }
        return fingerprintResponse;
    }

    @Override
    public void writeFingerprint(String username, String passwordHash) {
        mSharedPreferencesStorage.edit().putString(username, passwordHash).apply();
    }

    @Override
    public boolean canStoreFingerprintSecurely() {
        if (mBiometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
            return true;
        }
        return false;
    }

    @Override
    public void clearAllFingerprint() {
        mSharedPreferencesStorage.edit().clear().apply();
    }

    @Override
    public void clearSpecificFingerprint(String username) {
        mSharedPreferencesStorage.edit().remove(username).apply();
    }

    @Override
    public boolean isUserFingerprintSaved(String username) {
        return mSharedPreferencesStorage.getString(username, null) != null;
    }

}
