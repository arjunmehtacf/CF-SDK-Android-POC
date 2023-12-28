package com.example.cf_sdk.logic.changebanksdk.sanitization;

import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;

import io.reactivex.Single;

/**
 *
 * Ensures an address is properly sanitized.
 */

public class AddressSanitizer implements Sanitizer<AddAddressParameters, Single<AddAddressParameters>>{

    private final StringSanitizer mStringSanitizer;

    public AddressSanitizer(StringSanitizer stringSanitizer){
        mStringSanitizer = stringSanitizer;
    }

    @Override
    public Single<AddAddressParameters> sanitize(AddAddressParameters input) {
        String city = mStringSanitizer.sanitize(input.getCity());
        String state = mStringSanitizer.sanitize(input.getState());
        String zip = mStringSanitizer.sanitize(input.getZipCode());
        String country = mStringSanitizer.sanitize(input.getCountry());
        String street = mStringSanitizer.sanitize(input.getStreet());
        String unit = mStringSanitizer.sanitize(input.getUnit());
        AddAddressParameters sanitized = new AddAddressParameters(
                input.getHeaders(),
                street,
                unit,
                zip,
                city,
                state,
                country,"USA");

        return Single.just(sanitized);
    }
}
