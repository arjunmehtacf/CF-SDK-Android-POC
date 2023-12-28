package com.example.cf_sdk.logic.changebanksdk.validation;

import androidx.annotation.NonNull;

import com.example.cf_sdk.R;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 *
 * Phone Validation logic.
 */

public class PhoneValidator implements Validator<String, Single<String>> {
    private static final String US_CODE = "1";
    private static final int PHONE_LENGTH = 11;

    @Override
    public Single<String> validate(@NonNull String input) {
        Preconditions.checkNotNull(input);
        final ValidationError validationError = new ValidationError();

        if (input.length() != PHONE_LENGTH) {
            validationError.addErrorId(R.string.sdk_error_phone_length);
        }

        if (!input.startsWith(US_CODE)) {
            validationError.addErrorId(R.string.sdk_error_phone_code);
        }

        if (!validationError.hasError()) {
            return Single.just(input);
        } else {
            return Single.error(validationError);
        }
    }

}
