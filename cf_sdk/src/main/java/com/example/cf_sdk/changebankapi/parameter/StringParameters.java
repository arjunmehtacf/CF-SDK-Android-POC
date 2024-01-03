package com.example.cf_sdk.changebankapi.parameter;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * Generic String parameters.
 */

public class StringParameters extends Parameters {
    private String mFirstString;

    public static StringParameters create(@NonNull String firstString) {

        return new StringParameters(new HashMap<String, String>(), firstString);
    }

    public static StringParameters create(@NonNull Map<String, String> headers,
                                          @NonNull String firstString) {

        return new StringParameters(headers, firstString);
    }

    private StringParameters(Map<String, String> headers, String firstString) {
        super(headers);

        mFirstString = firstString;
    }


    public String getFirstString() {
        return mFirstString;
    }
}
