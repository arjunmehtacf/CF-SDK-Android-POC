package com.example.cf_sdk.changebankapi.util;

import android.util.Base64;


/**
 *
 * Implementation of {@link Encoder} using Android Base64.
 */

public class AndroidEncoder implements Encoder {
    @Override
    public String encodeToString(byte[] input) {
        return Base64.encodeToString(input, Base64.DEFAULT);
    }

    @Override
    public byte[] decode(String input) {
        return Base64.decode(input, Base64.DEFAULT);
    }
}
