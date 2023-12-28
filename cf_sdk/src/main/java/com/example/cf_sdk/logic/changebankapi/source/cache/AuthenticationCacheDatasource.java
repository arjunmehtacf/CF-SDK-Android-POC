package com.example.cf_sdk.logic.changebankapi.source.cache;

import com.example.cf_sdk.logic.changebanksdk.exception.SessionExpiredException;
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
import com.google.common.base.Optional;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *
 * Authentication cache datasource to provide in-memory data.
 */

public class AuthenticationCacheDatasource implements AuthenticationDatasource {

    private Session mSession;
    private String mUsername;

    @Override
    public Single<Session> getSession() {
        if (mSession != null) {
            return Single.just(mSession);
        } else {
            return Single.error(new SessionExpiredException());
        }
    }

    @Override
    public void saveSession(Session session) {
        mSession = session;
    }

    @Override
    public Single<Session> login(LoginParameters loginParameters) {
        throw new UnsupportedOperationException("AuthenticationCacheDatasource not support login");
    }

    @Override
    public Single<ChangebankResponse> updateCredentials(
            UpdateCredentialsParameters updateCredentialsParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support updateCredentials");
    }

    @Override
    public Single<LoginParameters.LoginMethodType> getLoginMethodType() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getLoginMethodType");
    }

    @Override
    public Single<Optional<byte[]>> getPinSalt() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getPinSalt");
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support checkUsernameExists");
    }

    @Override
    public Single<Optional<String>> getSavedUsername() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getSavedUsername");
    }

    @Override
    public Single<Optional<String>> getCachedUsername() {
        return Single.just(Optional.fromNullable(mUsername));
    }

    @Override
    public Single<ChangebankResponse> logout(Parameters parameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support logout");
    }

    @Override
    public Single<ChangebankResponse> changePassword(UpdatePasswordParameters updatePasswordParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support changePassword");
    }

    @Override
    public void savePinSalt(byte[] pinSalt, String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support savePinSalt");
    }

    @Override
    public void saveUsername(String username) {
        mUsername = username;
    }

    @Override
    public void clearAllAuthenticationDatasourceCache() {
        mUsername = null;
        mSession = null;
    }

    @Override
    public void clearPinAndFingerprint() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearPinAndFingerprint");
    }

    @Override
    public void updateLocalStorage(LoginParameters loginParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support updateLocalStorage");
    }

    @Override
    public Single<Boolean> checkPasscodeEnabled(CheckPasscodeEnabledParameters parameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support checkPasscodeEnabled");
    }

    @Override
    public Observable<FingerprintResponse> readFingerprint(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support readFingerprint");
    }

    @Override
    public Single<FingerprintResponse> readFingerprintInstant(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support readFingerprintInstant");    }

    @Override
    public Completable writeFingerprint(String username, String passwordHash) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support writeFingerprint");
    }

    @Override
    public Single<Boolean> canStoreFingerprintSecurely() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support canStoreFingerprintSecurely");
    }

    @Override
    public Completable clearAllFingerprint() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearAllFingerprint");
    }

    @Override
    public Single<Boolean> isUserFingerprintSaved(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support isUserFingerprintSaved");
    }

    @Override
    public Completable clearSpecificFingerprint(String username) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearSpecificFingerprint");
    }

    @Override
    public Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support resendLoginMFA");
    }

    @Override
    public Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support verifyLoginMFA");
    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support updateSecurityProfile");
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters) {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support getSecurityProfile");
    }

    @Override
    public Completable clearPasscode() {
        throw new UnsupportedOperationException(
                "AuthenticationCacheDatasource not support clearPasscode");
    }
}
