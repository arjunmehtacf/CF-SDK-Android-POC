package com.example.cf_sdk.logic.changebanksdk.sanitization;

/**
 *
 * Generic interface implemented by sanitizers.
 */

public interface Sanitizer<I, R> {
    R sanitize(I input);
}
