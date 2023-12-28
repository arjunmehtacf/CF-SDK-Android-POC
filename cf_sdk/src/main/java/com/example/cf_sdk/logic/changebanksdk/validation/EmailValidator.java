package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebanksdk.parameter.validation.EmailValidationParameters;

import java.util.regex.Pattern;

import io.reactivex.Single;

/**
 *
 * Validates email validation parameters.
 */

public class EmailValidator implements
        Validator<EmailValidationParameters, Single<EmailValidationParameters>> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "([A-Za-z0-9._+-]+)@([a-zA-Z0-9.-]+\\.[a-zA-Z0-9-]+)");


    @Override
    public Single<EmailValidationParameters> validate(EmailValidationParameters input) {
        final ValidationError validationError = new ValidationError();
        if (!EMAIL_PATTERN.matcher(input.getEmail()).matches()) {
            validationError.addErrorId(R.string.sdk_error_email_invalid);
        }

        if (!validationError.hasError()) {
            return Single.just(input);
        } else {
            return Single.error(validationError);
        }
    }
}
