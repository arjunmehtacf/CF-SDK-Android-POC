package com.example.cf_sdk.logic.changebankapi.source;

import com.example.cf_sdk.logic.changebanksdk.Header;
import com.example.cf_sdk.logic.changebanksdk.exception.PinNotSetException;
import com.example.cf_sdk.logic.changebanksdk.exception.UsernameNotFoundException;
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
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

/**
 *
 * Authentication repository is in charge of choosing the correct {@link AuthenticationDatasource}.
 */

public class AuthenticationRepository implements AuthenticationDatasource {
    private static final String TAG = AuthenticationRepository.class.getSimpleName();

    private AuthenticationDatasource mRemoteDatasource;
    private AuthenticationDatasource mCacheDatasource;
    private AuthenticationDatasource mLocalDatasource;

    private final Consumer<Session> saveSession = new Consumer<Session>() {
        @Override
        public void accept(Session session) throws Exception {
            saveSession(session);
        }
    };

    @Inject
    public AuthenticationRepository(@Named("remote") AuthenticationDatasource remoteDataSource,
                                    @Named("local") AuthenticationDatasource localDataSource,
                                    @Named("cache") AuthenticationDatasource cacheDataSource) {
        mRemoteDatasource = remoteDataSource;
        mLocalDatasource = localDataSource;
        mCacheDatasource = cacheDataSource;
    }

    @Override
    public Single<Session> login(final LoginParameters loginParameters) {
        return getPinSalt()
                .flatMap(new Function<Optional<byte[]>, SingleSource<? extends Session>>() {
                    @Override
                    public SingleSource<? extends Session> apply(Optional<byte[]> salt) throws Exception {
                        // if pin is not null then is a login with pin.
                        if (loginParameters.isLoginWithPin()) {
                            if (salt.isPresent()) {
                                loginParameters.generateHashedPin(salt.get());
                            } else {
                                return Single.error(new PinNotSetException());
                            }
                        }
                        return mRemoteDatasource.login(loginParameters);
                    }
                })
                .doOnSuccess(saveSession)
                .doOnSuccess(new Consumer<Session>() {
                    @Override
                    public void accept(Session session) throws Exception {
                        //Save username from input into cache repo
                        mCacheDatasource.saveUsername(loginParameters.getUsername());

                        //Save in Local storage
                        updateLocalStorage(loginParameters);
                    }
                });
    }

    @Override
    public Single<ChangebankResponse> updateCredentials(
            final UpdateCredentialsParameters params) {

        return getSession()
                .flatMap(new Function<Session, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(Session session) throws Exception {
                        params.setMemberId(session.getMemberId());
                        params.addHeader(Header.TOKEN, session.getToken());
                        return mRemoteDatasource.updateCredentials(params);
                    }
                })
                .flatMap(new Function<ChangebankResponse, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(final ChangebankResponse changebankResponse) throws Exception {
                        return getCachedUsername()
                                .flatMap(new Function<Optional<String>, SingleSource<? extends ChangebankResponse>>() {
                                    @Override
                                    public SingleSource<? extends ChangebankResponse> apply(Optional<String> username) throws Exception {
                                        if (username.isPresent()) {
                                            if (params.getSalt() != null) {
                                                //Save Salt with Username
                                                savePinSalt(params.getSalt(), username.get());
                                            }
                                            if (params.getBiometric() != null) {
                                                writeFingerprint(username.get(), params.getBiometric());
                                            }
                                        } else {
                                            Single.error(new UsernameNotFoundException());
                                        }
                                        return Single.just(changebankResponse);
                                    }
                                })
                                .onErrorReturn(new Function<Throwable, ChangebankResponse>() {
                                    @Override
                                    public ChangebankResponse apply(Throwable throwable) throws Exception {
                                        return changebankResponse;
                                    }
                                });
                    }
                });
    }

    @Override
    public Single<Session> getSession() {
        return mCacheDatasource.getSession();
    }

    @Override
    public Single<LoginParameters.LoginMethodType> getLoginMethodType() {
        return mLocalDatasource.getLoginMethodType();
    }

    @Override
    public Single<Optional<byte[]>> getPinSalt() {
        return mLocalDatasource.getPinSalt();
    }

    @Override
    public Single<ChangebankResponse> checkUsernameExists(UsernameParameters usernameParameters) {
        return mRemoteDatasource.checkUsernameExists(usernameParameters);
    }

    @Override
    public Single<Optional<String>> getSavedUsername() {
        return mLocalDatasource.getSavedUsername();
    }

    @Override
    public Single<Optional<String>> getCachedUsername() {
        return mCacheDatasource.getCachedUsername();
    }


    @Override
    public Single<ChangebankResponse> logout(Parameters parameters) {
        return mRemoteDatasource.logout(parameters);
    }

    @Override
    public Single<ChangebankResponse> changePassword(UpdatePasswordParameters updatePasswordParameters) {
        return mRemoteDatasource.changePassword(updatePasswordParameters)
                .flatMap(new Function<ChangebankResponse, SingleSource<? extends ChangebankResponse>>() {
                    @Override
                    public SingleSource<? extends ChangebankResponse> apply(ChangebankResponse changebankResponse) throws Exception {
                        clearPinAndFingerprint();
                        return Single.just(changebankResponse);
                    }
                });
    }

    @Override
    public Single<Boolean> checkPasscodeEnabled(final CheckPasscodeEnabledParameters parameters) {
        return Single.zip(
                getPinSalt(),
                getSavedUsername(),
                getCachedUsername(),
                new Function3<Optional<byte[]>, Optional<String>, Optional<String>, Boolean>() {
                    @Override
                    public Boolean apply(Optional<byte[]> salt,
                                         Optional<String> savedUsername,
                                         Optional<String> loggedInUsername) throws Exception {
                        if (parameters.getUsername() == null) {
                            return salt.isPresent() &&
                                    savedUsername.isPresent() &&
                                    loggedInUsername.isPresent() &&
                                    savedUsername.get().equalsIgnoreCase(loggedInUsername.get());
                        }

                        return salt.isPresent() &&
                                savedUsername.isPresent() &&
                                savedUsername.get().equalsIgnoreCase(parameters.getUsername());
                    }
                });
    }

    @Override
    public void saveSession(Session session) {
        mCacheDatasource.saveSession(session);
    }


    @Override
    public void savePinSalt(byte[] pinSalt, String username) {
        mLocalDatasource.savePinSalt(pinSalt, username);
    }

    @Override
    public void saveUsername(String username) {
        mLocalDatasource.saveUsername(username);
    }

    @Override
    public void clearAllAuthenticationDatasourceCache() {
        mCacheDatasource.clearAllAuthenticationDatasourceCache();
    }

    @Override
    public void clearPinAndFingerprint() {
        mLocalDatasource.clearPinAndFingerprint();
    }

    @Override
    public void updateLocalStorage(LoginParameters loginParameters) {
        mLocalDatasource.updateLocalStorage(loginParameters);
    }

    @Override
    public Observable<FingerprintResponse> readFingerprint(String username) {
        return mLocalDatasource.readFingerprint(username);
    }

    @Override
    public Single<FingerprintResponse> readFingerprintInstant(String username) {
        return mLocalDatasource.readFingerprintInstant(username);
    }

    @Override
    public Completable writeFingerprint(String username, String passwordHash) {
        return mLocalDatasource.writeFingerprint(username, passwordHash);
    }

    @Override
    public Single<Boolean> canStoreFingerprintSecurely() {
        return mLocalDatasource.canStoreFingerprintSecurely();
    }

    @Override
    public Completable clearAllFingerprint() {
        return mLocalDatasource.clearAllFingerprint();
    }

    @Override
    public Single<Boolean> isUserFingerprintSaved(String username) {
        return mLocalDatasource.isUserFingerprintSaved(username);
    }

    @Override
    public Completable clearSpecificFingerprint(String username) {
        return mLocalDatasource.clearSpecificFingerprint(username);
    }

    @Override
    public Single<Session> resendLoginMFA(ResendTwoFactorParameters resendTwoFactorParameters) {
        return mRemoteDatasource.resendLoginMFA(resendTwoFactorParameters)
                .doOnSuccess(saveSession);
    }

    @Override
    public Single<Session> verifyLoginMFA(TwoFactorParameters twoFactorParameters) {
        return mRemoteDatasource.verifyLoginMFA(twoFactorParameters)
                .doOnSuccess(saveSession);
    }

    @Override
    public Single<ChangebankResponse> updateSecurityProfile(SecurityProfileParameters securityProfileParameters) {
        return mRemoteDatasource.updateSecurityProfile(securityProfileParameters);
    }

    @Override
    public Single<SecurityProfile> getSecurityProfile(GetSecurityProfileParameters getSecurityProfileParameters) {
        return mRemoteDatasource.getSecurityProfile(getSecurityProfileParameters);
    }

    @Override
    public Completable clearPasscode() {
        return mLocalDatasource.clearPasscode();
    }

}
