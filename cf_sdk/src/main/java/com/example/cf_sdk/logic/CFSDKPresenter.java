package com.example.cf_sdk.logic;

import com.example.cf_sdk.defifination.CFCallProvider;
import com.example.cf_sdk.logic.changebankapi.network.api.ApiConfig;
import com.example.cf_sdk.logic.changebanksdk.ChangebankSingleObserver;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.changebanksdk.model.member.version.VersionConfig;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.LoginParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.SettingsParameter;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.LoginTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.VersionConfigTask;
import com.example.cf_sdk.logic.sdk_module.CFSDKContract;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CFSDKPresenter implements CFCallProvider, CFSDKContract.Presenter {
    VersionConfigTask mVersionConfigTask;
    CFSDKContract.View mView;

    LoginTask loginTask;

    @Inject
    public CFSDKPresenter(VersionConfigTask versionConfigTask, CFSDKContract.View view, LoginTask mLoginTask) {
        mVersionConfigTask = versionConfigTask;
        mView = view;
        loginTask = mLoginTask;
    }

    @Override
    public void getSettingsData(String appId, String url, CFSDKResponseCallback callback) {
        Map<String, String> apiconfigHeader = new HashMap<>();
        apiconfigHeader.put(ApiConfig.USER_URL, url);
        SettingsParameter settingsParameter = new SettingsParameter(apiconfigHeader);
        settingsParameter.setApplicationId(appId);
        mVersionConfigTask.execute(new ChangebankSingleObserver<VersionConfig>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(VersionConfig versionConfig) {
                callback.onSuccess(versionConfig);
            }

            @Override
            public void onError(Throwable e) {
                try {
                    callback.onFailure(e);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, settingsParameter);
    }

    @Override
    public void login(String username, String password, String url, CFSDKResponseCallback<Session> callback, String app_id) {
        try {
            LoginParameters loginParameters = LoginParameters.createWithPassword(username, password, "", false, "");
            loginParameters.getHeaders().put(ApiConfig.USER_URL, url);
            loginParameters.getHeaders().put(ApiConfig.APP_ID, app_id);
            loginTask.execute(new ChangebankSingleObserver<Session>() {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(Session session) {
                    callback.onSuccess(session);

                }

                @Override
                public void onError(Throwable e) {
                    try {
                        callback.onFailure(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }, loginParameters);
        } catch (UnsupportedEncodingException e) {
        }
    }
}
