package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.AccountApi;
import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link AccountApi} creator.
 */

public class AccountApiCreator implements ApiCreator<AccountApi> {
    @Override
    public AccountApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(AccountApi.class);
    }
}
