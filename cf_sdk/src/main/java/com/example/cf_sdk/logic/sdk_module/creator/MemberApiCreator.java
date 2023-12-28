package com.example.cf_sdk.logic.sdk_module.creator;

import com.example.cf_sdk.logic.changebankapi.network.api.ApiCreator;
import com.example.cf_sdk.logic.changebankapi.network.api.MemberApi;
import com.example.cf_sdk.logic.changebanksdk.log.Logger;

import retrofit2.Retrofit;

/**
 *
 * Network {@link MemberApi} creator.
 */

public class MemberApiCreator implements ApiCreator<MemberApi> {
    @Override
    public MemberApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(MemberApi.class);
    }
}
