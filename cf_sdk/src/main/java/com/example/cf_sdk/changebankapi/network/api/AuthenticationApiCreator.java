package com.example.cf_sdk.changebankapi.network.api;

import com.example.cf_sdk.changebankapi.log.Logger;

import retrofit2.Retrofit;

public class AuthenticationApiCreator implements ApiCreator<AuthenticationApi> {
    @Override
    public AuthenticationApi create(Logger logger, Retrofit retrofit) {
        return retrofit.create(AuthenticationApi.class);
    }
}