package com.example.cf_sdk.logic.sdk_module;

import com.example.cf_sdk.logic.CFSDKPresenter;
import com.example.cf_sdk.logic.changebanksdk.task.authentication.LoginTask;
import com.example.cf_sdk.logic.changebanksdk.task.member.VersionConfigTask;

import dagger.Module;
import dagger.Provides;

@Module
public class CFSDKModule {

    private final CFSDKContract.View mWelcomeView;

    public CFSDKModule(CFSDKContract.View view) {
        mWelcomeView = view;
    }

    @Provides
    CFSDKContract.View provideWelcomeView() {
        return mWelcomeView;
    }

    @Provides
    CFSDKContract.Presenter provideAPIPresenter(VersionConfigTask versionConfigTask, CFSDKContract.View view, LoginTask mLoginTask) {
        return new CFSDKPresenter(versionConfigTask, view, mLoginTask);
    }
}
