package com.example.cf_sdk.logic.changebankapi.source.remote;

import com.example.cf_sdk.logic.changebankapi.network.api.GoogleApi;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;
import com.example.cf_sdk.logic.changebanksdk.source.GoogleDatasource;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 *
 * Remote datasource to call Member endpoints.
 */

public class GoogleRemoteDatasource implements GoogleDatasource {
    private final String mApiKey;
    private GoogleApi mGoogleApi;

    @Inject
    public GoogleRemoteDatasource(GoogleApi googleApi, String apiKey) {
        mGoogleApi = googleApi;
        mApiKey = apiKey;
    }

    @Override
    public Single<PlaceDetailSerializer> getPlaceDetail(String placeId) {
        return mGoogleApi.getPlace(mApiKey, placeId);
    }

    @Override
    public Single<PlaceAutocompleteSerializer> getPlacePredictions(String input) {
        return mGoogleApi.getAutocompletePredictions(mApiKey, input);
    }

}
