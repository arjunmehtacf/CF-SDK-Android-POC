package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.Endpoints;
import com.example.cf_sdk.logic.changebankapi.network.api.ApiConfig;
import com.example.cf_sdk.logic.changebankapi.network.api.AuthenticationApi;
import com.example.cf_sdk.logic.changebankapi.source.AuthenticationRepository;
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

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *
 * Remote datasource to call authentication endpoints.
 */

public class AuthenticationRemoteDatasource implements AuthenticationDatasource {
    private static final String TAG = AuthenticationRepository.class.getSimpleName();
    private AuthenticationApi mAuthenticationApi;

    @Inject
    public AuthenticationRemoteDatasource(AuthenticationApi authenticationApi) {
        mAuthenticationApi = authenticationApi;
    }

    @Override
    public Single<Session> login(LoginParameters loginParameters) {
        return mAuthenticationApi.login(loginParameters.getHeaders().get(ApiConfig.USER_URL) + Endpoints.Auth.AUTHENTICATION, loginParameters.getHeaders(), loginParameters);
    }

    @Override
    public Single<ChangebankResponse> updateCredentials(UpdateCredentialsParameters updateCredentialsParameters) {
        return mAuthenticationApi.updateCredentials(updateCredentialsParameters.getHeaders(), updateCredentialsParameters.getMemberId(), updateCredentialsParameters);
    }

    @Override
    public Single<Session> getSession() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support getSession");
    }

    @Override
    public Single<LoginParameters.LoginMethodType> getLoginMethodType() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support getLoginMethodType");
    }

    @Override
    public Single<Optional<byte[]>> getPinSalt() {
        throw new UnsupportedOperationException("AuthenticationCacheDatasource not support getPinSalt");
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters) {
        return mAuthenticationApi.checkUsernameExists(usernameParameters.getHeaders(), usernameParameters);
    }

    @Override
    public Single<Optional<String>> getSavedUsername() {
        throw new UnsupportedOperationException("AuthenticationCacheDatasource not support getSavedUsername");
    }

    @Override
    public Single<Optional<String>> getCachedUsername() {
        throw new UnsupportedOperationException("AuthenticationCacheDatasource not support getCachedUsername");
    }

    @Override
    public Single<ChangebankResponse> logout(Parameters parameters) {
        return mAuthenticationApi.logout(parameters.getHeaders());
    }

    @Override
    public Single<ChangebankResponse> changePassword(UpdatePasswordParameters updatePasswordParameters) {
        return mAuthenticationApi.changePassword(updatePasswordParameters.getHeaders(), updatePasswordParameters);
    }

    @Override
    public Single<Boolean> checkPasscodeEnabled(CheckPasscodeEnabledParameters parameters) {
        throw new UnsupportedOperationException("AuthenticationCacheDatasource not support checkPasscodeEnabled");
    }

    @Override
    public void saveSession(Session session) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support saveSession");
    }

    @Override
    public void savePinSalt(byte[] pinSalt, String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support savePinSalt");
    }

    @Override
    public void saveUsername(String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support saveUsername");
    }

    @Override
    public void clearAllAuthenticationDatasourceCache() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support clearAllAuthenticationDatasourceCache");
    }

    @Override
    public void clearPinAndFingerprint() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support clearPinAndFingerprint");
    }

    @Override
    public void updateLocalStorage(LoginParameters loginParameters) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support updateLocalStorage");
    }

    @Override
    public Observable<FingerprintResponse> readFingerprint(String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support readFingerprint");
    }

    @Override
    public Single<FingerprintResponse> readFingerprintInstant(String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support readFingerprintInstant");
    }

    @Override
    public Completable writeFingerprint(String username, String passwordHash) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support writeFingerprint");
    }

    @Override
    public Single<Boolean> canStoreFingerprintSecurely() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support canStoreFingerprintSecurely");
    }

    @Override
    public Completable clearAllFingerprint() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support clearAllFingerprint");
    }

    @Override
    public Single<Boolean> isUserFingerprintSaved(String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support isUserFingerprintSaved");
    }

    @Override
    public Completable clearSpecificFingerprint(String username) {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support clearSpecificFingerprint");
    }

    @Override
    public Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters) {
        return mAuthenticationApi.resendCode(resendTwoFactorParameters.getHeaders(), resendTwoFactorParameters.getMemberId());
    }

    @Override
    public Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters) {
        return mAuthenticationApi.twoFactor(twoFactorParameters.getHeaders(), twoFactorParameters);
    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters) {
        return mAuthenticationApi.updateSecurityProfile(securityProfileParameters.getHeaders(), securityProfileParameters.getMemberId(), securityProfileParameters);
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters) {
        return mAuthenticationApi.getSecurityProfile(getSecurityProfileParameters.getHeaders(), getSecurityProfileParameters.getMemberId());
    }

    @Override
    public Completable clearPasscode() {
        throw new UnsupportedOperationException("AuthenticationRemoteDatasource not support clearPasscode");
    }
}
