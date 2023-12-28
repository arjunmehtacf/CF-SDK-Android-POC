package com.example.cf_sdk.logic.changebanksdk.source;

import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceAutocompleteSerializer;
import com.example.cf_sdk.logic.changebanksdk.model.google.PlaceDetailSerializer;

import io.reactivex.Single;

/**
 *
 * Datasource for Google Places API.
 */

public interface GoogleDatasource {
    Single<PlaceDetailSerializer> getPlaceDetail(String placeId );
    Single<PlaceAutocompleteSerializer> getPlacePredictions(String input );

}
