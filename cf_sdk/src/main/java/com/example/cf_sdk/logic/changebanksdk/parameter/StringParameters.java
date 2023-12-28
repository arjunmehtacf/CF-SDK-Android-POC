package com.example.cf_sdk.logic.changebanksdk.parameter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import dagger.internal.Preconditions;

/**
 *
 * Generic String parameters.
 */

public class StringParameters extends Parameters {
    private String mFirstString;

    public static StringParameters create(@Nonnull String firstString) {
        Preconditions.checkNotNull(firstString);
        return new StringParameters(new HashMap<String, String>(), firstString);
    }

    public static StringParameters create(@Nonnull Map<String, String> headers,
                                          @Nonnull String firstString) {
        Preconditions.checkNotNull(firstString);
        Preconditions.checkNotNull(headers);
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
