package com.example.cf_sdk.logic.changebankapi.util;

import android.util.Base64;

import com.example.cf_sdk.logic.changebanksdk.util.Encoder;

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
