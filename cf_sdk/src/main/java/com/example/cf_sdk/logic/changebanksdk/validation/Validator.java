package com.example.cf_sdk.logic.changebanksdk.validation;

/**
 *
 * Generic interface implemented by validators.
 */

public interface Validator<I, R> {
    R validate(I input);
}
