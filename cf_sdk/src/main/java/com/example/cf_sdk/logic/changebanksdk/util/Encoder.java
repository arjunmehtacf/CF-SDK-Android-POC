package com.example.cf_sdk.logic.changebanksdk.util;

/**
 *
 * Interface to help mocking
 */

public interface Encoder {
    String encodeToString(byte[] input);
    byte[] decode(String input);
}
