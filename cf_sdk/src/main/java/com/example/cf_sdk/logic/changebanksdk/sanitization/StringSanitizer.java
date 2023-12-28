package com.example.cf_sdk.logic.changebanksdk.sanitization;

/**
 * Trim the string using this class.
 */

public class StringSanitizer implements Sanitizer<String, String>{


    @Override
    public String sanitize(String input) {
        return input != null ? input.trim():null;
    }
}
