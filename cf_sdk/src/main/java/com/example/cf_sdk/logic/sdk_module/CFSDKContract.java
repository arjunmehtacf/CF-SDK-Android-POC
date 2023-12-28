package com.example.cf_sdk.logic.sdk_module;

import com.example.cf_sdk.logic.CFSDKResponseCallback;
import com.example.cf_sdk.logic.changebanksdk.model.Session;

/**
 * MVP Contract for CF SDK.
 */

public class CFSDKContract {

    public interface View {
        void showProgress();

        void hideProgress();
    }

    public interface Presenter {
        void getSettingsData(String appId, String os, CFSDKResponseCallback callback);

        void login(String username, String password, String url, CFSDKResponseCallback<Session> callback, String appId);
    }
}
