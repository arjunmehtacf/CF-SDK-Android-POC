package com.example.cf_sdk.changebankapi.source.datasource

import com.example.cf_sdk.changebankapi.model.google.PlaceAutocompleteSerializer
import com.example.cf_sdk.changebankapi.model.google.PlaceDetailSerializer
import io.reactivex.Single

/**
 *
 * Datasource for Google Places API.
 */
interface GoogleDatasource {
    fun getPlaceDetail(placeId: String?): Single<PlaceDetailSerializer?>?
    fun getPlacePredictions(input: String?): Single<PlaceAutocompleteSerializer?>?
}