package com.example.cf_sdk.defifination;

import android.app.Application;

import com.example.cf_sdk.logic.CFSDKResponseCallback;
import com.example.cf_sdk.logic.changebanksdk.model.Session;
import com.example.cf_sdk.logic.sdk_module.CFSDKComponent;
import com.example.cf_sdk.logic.sdk_module.CFSDKContract;
import com.example.cf_sdk.logic.sdk_module.CFSDKModule;
import com.example.cf_sdk.logic.sdk_module.ChangebankApp;
import com.example.cf_sdk.logic.sdk_module.DaggerCFSDKComponent;

import javax.inject.Inject;

public class CFApiCall implements CFSDKContract.View {

    private static CFApiCall instance;

    @Inject
    CFSDKContract.Presenter mCFSDKPresenter;

    private CFSDKComponent mCFSDKComponent;

    // Private constructor to enforce singleton pattern
    private CFApiCall(Application application) {
        initSDK(application);
    }

    public static synchronized CFApiCall getInstance(Application application) {
        if (instance == null) {
            instance = new CFApiCall(application);
        }
        return instance;
    }

    public void initSDK(Application application) {
        getWelcomeActivityComponent(application).inject(this);
    }

    public void callGetSettings(String appId, String url, CFSDKResponseCallback callback) {
        mCFSDKPresenter.getSettingsData(appId, url, callback);
    }

    public void callLoginAPI(String username, String password, String url, String appId, CFSDKResponseCallback<Session> callback) {
        mCFSDKPresenter.login(username, password, url, callback, appId);
    }

    public CFSDKComponent getWelcomeActivityComponent(Application application) {
        if (mCFSDKComponent == null) {
            ChangebankApp app = ((ChangebankApp) application);
            mCFSDKComponent = DaggerCFSDKComponent.builder()
                    .changebankComponent(app.getChangebankComponent())
                    .cFSDKModule(new CFSDKModule(this))
                    .build();
        }
        return mCFSDKComponent;
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    public void setSDKUrl(String url) {

    }
}
