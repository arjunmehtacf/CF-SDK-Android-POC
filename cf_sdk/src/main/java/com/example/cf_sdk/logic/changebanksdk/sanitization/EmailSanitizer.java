package com.example.cf_sdk.logic.changebanksdk.sanitization;

import com.example.cf_sdk.logic.changebanksdk.parameter.validation.EmailValidationParameters;

import io.reactivex.Single;

/**
 *
 * Email Sanitizer
 */

public class EmailSanitizer implements
        Sanitizer<EmailValidationParameters, Single<EmailValidationParameters>> {
    @Override
    public Single<EmailValidationParameters> sanitize(EmailValidationParameters input) {
        return Single.just(EmailValidationParameters.create(input.getEmail().trim()));
    }
}
