package com.example.cf_sdk.logic.changebanksdk.validation;

import androidx.annotation.NonNull;

import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebanksdk.parameter.authentication.UsernameParameters;

import java.util.regex.Pattern;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 *
 * Username Validation logic.
 */

public class UsernameValidator implements Validator<UsernameParameters, Single<UsernameParameters>> {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 20;

    private final StringValidator mStringValidator;
    private static final Pattern sPattern
            = Pattern.compile("(?!.*[\\-\\_]{2,})^[a-zA-Z0-9\\-\\_\\s]+");

    public UsernameValidator(StringValidator stringValidator) {
        mStringValidator = stringValidator;
    }

    @Override
    public Single<UsernameParameters> validate(@NonNull UsernameParameters input) {
        Preconditions.checkNotNull(input);
        final ValidationError validationError = new ValidationError();

        if (!mStringValidator.validate(input.getUsername())) {
            validationError.addErrorId(R.string.sdk_error_required_field);
        }

        if (input.getUsername() != null) {

            if (mStringValidator.containsWhiteSpaces(input.getUsername())) {
                validationError.addErrorId(R.string.sdk_error_white_space);
            }

            if (input.getUsername().startsWith("_") || input.getUsername().startsWith("-")) {
                validationError.addErrorId(R.string.sdk_error_usernmae_invalid_first_char);
            }

            if (!sPattern.matcher(input.getUsername()).matches()) {
                validationError.addErrorId(R.string.sdk_error_username_invalid_characters);
            }


            if (input.getUsername().length() < MIN_LENGTH) {
                validationError.addErrorId(R.string.sdk_error_username_short);
            }

            if (input.getUsername().length() > MAX_LENGTH) {
                validationError.addErrorId(R.string.sdk_error_username_long);
            }
        }

        if (!validationError.hasError()) {
            return Single.just(input);
        } else {
            return Single.error(validationError);
        }
    }
}
