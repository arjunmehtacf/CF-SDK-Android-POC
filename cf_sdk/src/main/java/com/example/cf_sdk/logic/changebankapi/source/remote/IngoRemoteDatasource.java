package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.network.api.IngoApi;
import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IngoResponse;
import com.example.cf_sdk.logic.changebanksdk.source.IngoDatasource;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 *
 * Remote datasource to call Ingo endpoints.
 */

public class IngoRemoteDatasource implements IngoDatasource {
    private IngoApi mIngoApi;

    @Inject
    public IngoRemoteDatasource(IngoApi ingoApi) {
        mIngoApi = ingoApi;
    }

    @Override
    public Single<IngoResponse> verifyIngoSSSN(VerifyIngoSSNParameters verifyIngoSSNParameters) {
        return mIngoApi.verifyIngoSSSN(verifyIngoSSNParameters.getHeaders(), verifyIngoSSNParameters);
    }

    @Override
    public Single<IngoResponse> getIngoSSOToken(StringParameters stringParameters) {
        return mIngoApi.getIngoSSOToken(stringParameters.getHeaders(), stringParameters.getFirstString());
    }

}
