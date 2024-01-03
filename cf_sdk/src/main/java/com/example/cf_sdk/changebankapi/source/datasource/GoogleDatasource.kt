package com.example.sdk_no_dagger.changebankapi.source.datasource

import com.example.sdk_no_dagger.changebankapi.model.google.PlaceAutocompleteSerializer
import com.example.sdk_no_dagger.changebankapi.model.google.PlaceDetailSerializer
import io.reactivex.Single

/**
 * Created by gunveernatt on 8/17/17.
 *
 * Datasource for Google Places API.
 */
interface GoogleDatasource {
    fun getPlaceDetail(placeId: String?): Single<PlaceDetailSerializer?>?
    fun getPlacePredictions(input: String?): Single<PlaceAutocompleteSerializer?>?
}