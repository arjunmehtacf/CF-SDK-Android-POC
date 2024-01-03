package com.example.cf_sdk.changebankapi.parameter.google;




import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles login information to authenticate.
 */

public class PlaceDetailParameters extends Parameters {

    private final transient String mPlaceId;
    private final Map<String, String> mHeaders;
    private final String mUnit;
    private final String mAddressLine1;


    public static PlaceDetailParameters Create(String placeId){
        return new PlaceDetailParameters(new HashMap<String, String>(), placeId, null, null);
    }

    public PlaceDetailParameters(Map<String, String> headers, String placeId, String unit, String addressLine1)  {
        super(null);
        mHeaders = headers;
        mPlaceId = placeId;
        mUnit = unit;
        mAddressLine1 = addressLine1;
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public String getUnit() {
        return mUnit;
    }

    public String getAddressLine1() {
        return mAddressLine1;
    }
}
