package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.parameter.StringParameters;
import com.example.cf_sdk.logic.changebanksdk.parameter.ingo.VerifyIngoSSNParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IngoResponse;

import io.reactivex.Single;

/**
 *
 * Datasource for Ingo API.
 */

public interface IngoDatasource {

    Single<IngoResponse> verifyIngoSSSN(VerifyIngoSSNParameters verifyIngoSSNParameters);

    Single<IngoResponse> getIngoSSOToken(StringParameters stringParameters);

}
