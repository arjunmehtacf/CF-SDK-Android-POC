package com.example.cf_sdk.changebankapi.source.local;

import android.content.SharedPreferences;


import com.example.cf_sdk.changebankapi.fingerprint.FingerprintHelper;
import com.example.cf_sdk.changebankapi.model.FingerprintResponse;
import com.example.cf_sdk.changebankapi.model.Session;
import com.example.cf_sdk.changebankapi.model.member.SecurityProfile;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.GetSecurityProfileParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginMethodType;
import com.example.cf_sdk.changebankapi.parameter.authentication.LoginParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.ResendTwoFactorParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.changebankapi.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.defination.response.ChangebankResponse;
import com.example.cf_sdk.changebankapi.source.remote.AuthenticationDatasource;
import com.example.cf_sdk.changebankapi.util.Encoder;
import com.google.common.base.Optional;



import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *
 * Local stored data related to Authentication.
 */

public class AuthenticationLocalDataSource
        implements AuthenticationDatasource {
    public static final String AUTHENTICATION_PREFERENCES_FILE = "AuthenticationPreferences";
    public static final String BIOMETRIC_PREFERENCES_FILE = "BIOMETRIC_PREFERENCES_FILE";
    static final String KEY_LOGIN_METHOD_TYPE = "KeyLoginMethodType";
    static final String KEY_PIN_SALT = "KeyPinSalt";
    static final String KEY_USERNAME = "KeyUsername";
    private final FingerprintHelper mWhorlwindFingerprintHelper;

    private SharedPreferences mAuthenticationPreferences;
    private Encoder mEncoder;


    public AuthenticationLocalDataSource(SharedPreferences sharedPreferences,
                                         Encoder encoder,
                                         FingerprintHelper fingerprintHelper) {
        mAuthenticationPreferences = sharedPreferences;
        mEncoder = encoder;
        mWhorlwindFingerprintHelper = fingerprintHelper;
    }

    @Override
    public Single<LoginMethodType> getLoginMethodType() {
        LoginMethodType loginMethodType = LoginMethodType.valueOf(mAuthenticationPreferences.getString(KEY_LOGIN_METHOD_TYPE, LoginMethodType.UNKNOWN.name()));
        return Single.just(loginMethodType);
    }

    @Override
    public void savePinSalt(byte[] pinSalt, String username) {
        String pinSaltString = mEncoder.encodeToString(pinSalt);
        SharedPreferences.Editor editor = mAuthenticationPreferences.edit();
        editor.putString(KEY_PIN_SALT, pinSaltString);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    @Override
    public void saveUsername(String username) {
        SharedPreferences.Editor editor = mAuthenticationPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    @Override
    public void clearAllAuthenticationDatasourceCache() {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support clearAllAuthenticationDatasourceCache");
    }

    @Override
    public void clearPinAndFingerprint() {
        clearAllFingerprint();
        clearPasscode();
    }

    @Override
    public Single<Optional<byte[]>> getPinSalt() {
        String pinSaltString = mAuthenticationPreferences.getString(KEY_PIN_SALT, "");
        byte[] pinSalt = null;
        if (!pinSaltString.isEmpty()) {
            pinSalt = mEncoder.decode(pinSaltString);
        }
        return Single.just(Optional.fromNullable(pinSalt));
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support checkUsernameExists");
    }

    @Override
    public Single<Optional<String>> getSavedUsername() {
        return Single.just(Optional.fromNullable(
                mAuthenticationPreferences.getString(KEY_USERNAME, null)));
    }

    @Override
    public Single<Optional<String>> getCachedUsername() {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support getCachedUsername");
    }


    @Override
    public Single<ChangebankResponse> logout(Parameters parameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support logout");
    }



    @Override
    public void updateLocalStorage(final LoginParameters loginParameters) {
        //If the new username is different from the one saved in preference clear all local storage for the user

        if (loginParameters.getLoginMethod() == LoginMethodType.PASSWORD_LOGIN) {
            String oldUsername = mAuthenticationPreferences.getString(
                    KEY_USERNAME,
                    null);

            if (oldUsername != null && !oldUsername.equalsIgnoreCase(loginParameters.getUsername())) {
                //Clear All Storage
                clearAllLocalStorage();
            }

            if (loginParameters.getShouldSaveUsername()) {
                saveUsername(loginParameters.getUsername());
            } else {
                clearUsername();
            }
        }

        saveIsLoginMethodType(loginParameters.getLoginMethod());
    }

    private void saveIsLoginMethodType(LoginMethodType loginMethod) {
        SharedPreferences.Editor editor = mAuthenticationPreferences.edit();
        editor.putString(KEY_LOGIN_METHOD_TYPE, loginMethod.name());
        editor.apply();
    }

    @Override
    public Observable<FingerprintResponse> readFingerprint(String username) {
        return Observable.just(mWhorlwindFingerprintHelper.readFingerprint(username));
    }

    @Override
    public Single<FingerprintResponse> readFingerprintInstant(String username) {
        return Single.just(mWhorlwindFingerprintHelper.readFingerprint(username));
    }

    @Override
    public Completable writeFingerprint(String username, String passwordHash) {
        saveUsername(username);
        mWhorlwindFingerprintHelper.writeFingerprint(username, passwordHash);

        return Completable.complete();
    }

    @Override
    public Single<Boolean> canStoreFingerprintSecurely() {
        return Single.just(mWhorlwindFingerprintHelper.canStoreFingerprintSecurely());
    }

    @Override
    public Completable clearAllFingerprint() {
        mWhorlwindFingerprintHelper.clearAllFingerprint();
        return Completable.complete();
    }

    @Override
    public Single<Boolean> isUserFingerprintSaved(String username) {
        return Single.just(mWhorlwindFingerprintHelper.isUserFingerprintSaved(username));
    }

    @Override
    public Completable clearSpecificFingerprint(String username) {
        mWhorlwindFingerprintHelper.clearSpecificFingerprint(username);
        return Completable.complete();
    }

    @Override
    public Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support resendLoginMFA");
    }

    @Override
    public Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support verifyLoginMFA");
    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support updateSecurityProfile");
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support getSecurityProfile");
    }

    @Override
    public Completable clearPasscode() {
        SharedPreferences.Editor editor = mAuthenticationPreferences.edit();
        editor.remove(KEY_PIN_SALT);
        editor.apply();
        return Completable.complete();
    }

    private void clearAllLocalStorage() {
        mAuthenticationPreferences.edit().clear().apply();
        mWhorlwindFingerprintHelper.clearAllFingerprint();
    }

    private void clearUsername() {
        SharedPreferences.Editor editor = mAuthenticationPreferences.edit();
        editor.remove(KEY_USERNAME);
        editor.apply();
    }


    @Override
    public Single<Session> getSession() {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support getSession");
    }

    @Override
    public void saveSession(Session session) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support saveSession");
    }


}
