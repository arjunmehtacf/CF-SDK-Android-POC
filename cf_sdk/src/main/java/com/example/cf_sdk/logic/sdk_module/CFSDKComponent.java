package com.example.cf_sdk.logic.sdk_module;



import android.app.Activity;

import com.example.cf_sdk.defifination.CFApiCall;
import com.example.cf_sdk.logic.sdk_module.singleton.ChangebankComponent;
import com.example.cf_sdk.logic.sdk_module.singleton.UseCaseModule;

import dagger.Component;

/**
 * Component interface for CFSDK and usecase module
 */
@ActivityScope
@Component(modules = {CFSDKModule.class, UseCaseModule.class}, dependencies = {ChangebankComponent.class})
public interface CFSDKComponent {
    void inject(Activity welcomeActivity);
    void inject(CFSDKContract.View view);
    void inject(CFApiCall cfApiCall);
}
