package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.parameter.member.IdscanParameters;
import com.example.cf_sdk.logic.changebanksdk.response.IdscanResponse;

import io.reactivex.Single;

/**
 * Created by victorojeda on 10/23/17.
 *
 *
 */

public interface  VerificationDatasource {
    Single<IdscanResponse> submitIdscan(IdscanParameters idscanParameters);
}
