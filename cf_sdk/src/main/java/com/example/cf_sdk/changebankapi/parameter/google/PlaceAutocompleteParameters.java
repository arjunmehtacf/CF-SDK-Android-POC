package com.example.cf_sdk.changebankapi.parameter.google;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

/**
 *
 * Handles login information to authenticate.
 */

public class PlaceAutocompleteParameters extends Parameters {

    private final String mSearchInput;


    public PlaceAutocompleteParameters(String placeId)  {
        super(null);
        mSearchInput = placeId;
    }

    public String getSearchInput() {
        return mSearchInput;
    }

}
