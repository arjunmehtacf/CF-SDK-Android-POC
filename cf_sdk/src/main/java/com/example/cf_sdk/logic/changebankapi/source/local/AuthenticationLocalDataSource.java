package com.example.cf_sdk.logic.changebankapi.source.local;

import android.content.SharedPreferences;

import com.example.cf_sdk.logic.changebanksdk.fingerprint.FingerprintHelper;
import com.example.cf_sdk.logic.changebanksdk.model.FingerprintResponse;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.SecurityProfile;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.CheckPasscodeEnabledParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.GetSecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.ResendTwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.SecurityProfileParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.TwoFactorParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdateCredentialsParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UpdatePasswordParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.source.AuthenticationDatasource;
import com.example.cf_sdk.logic.changebanksdk.util.Encoder;
import com.google.common.base.Optional;

import javax.inject.Inject;

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

    @Inject
    public AuthenticationLocalDataSource(SharedPreferences sharedPreferences,
                                         Encoder encoder,
                                         FingerprintHelper fingerprintHelper) {
        mAuthenticationPreferences = sharedPreferences;
        mEncoder = encoder;
        mWhorlwindFingerprintHelper = fingerprintHelper;
    }

    @Override
    public Single<LoginParameters.LoginMethodType> getLoginMethodType() {
        LoginParameters.LoginMethodType loginMethodType = LoginParameters.LoginMethodType.valueOf(mAuthenticationPreferences.getString(KEY_LOGIN_METHOD_TYPE, LoginParameters.LoginMethodType.UNKNOWN.name()));
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
    public Single<ChangebankResponse> changePassword(UpdatePasswordParameters updatePasswordParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support changePassword");
    }

    @Override
    public void updateLocalStorage(final LoginParameters loginParameters) {
        //If the new username is different from the one saved in preference clear all local storage for the user

        if (loginParameters.getLoginMethod() == LoginParameters.LoginMethodType.PASSWORD_LOGIN) {
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

    private void saveIsLoginMethodType(LoginParameters.LoginMethodType loginMethod) {
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
    public Single<Session> login(LoginParameters loginParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support login");
    }

    @Override
    public Single<ChangebankResponse> updateCredentials(
            UpdateCredentialsParameters updateCredentialsParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support updateCredentials");
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

    @Override
    public Single<Boolean> checkPasscodeEnabled(CheckPasscodeEnabledParameters parameters) {
        throw new UnsupportedOperationException(
                "AuthenticationRemoteDatasource not support checkPasscodeEnabled");
    }

}
